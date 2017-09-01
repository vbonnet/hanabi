package com.hanabi.model.impl;

import com.hanabi.model.facade.action.*;
import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.clue.Clue;
import com.hanabi.model.facade.player.Player;
import com.hanabi.model.facade.player.PlayerClue;
import com.hanabi.model.facade.player.PlayerGameView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GameEngine {

  private final GameState state;
  private final List<Player> players;

  public GameEngine(List<Player> players) throws Exception {
    int numberOfPlayers = players.size();
    if (numberOfPlayers < 2) {
      throw new Exception("Too few players");
    } else if (numberOfPlayers > 5) {
      throw new Exception("Too many players");
    }

    state = new GameState(players, null);
    this.players = Collections.unmodifiableList(players);
  }

  public GameState getState() {
    return state;
  }

  public void initialize() {
    for (Player player : players) {
      PlayerGameView view = new PlayerGameViewImpl(state, player);
      player.initializeWithView(view);
      player.initializeWithHand(
          new ArrayList<>(state.getPlayerHand(player).getPlaceholders()));
    }
  }

  public int run() throws Exception {
    Player currentPlayer = null;
    Player nextPlayer = players.get(0);

    // While there are still cards left in the deck, continue doing turns.
    boolean cardsRemaining = true;
    while (cardsRemaining) {
      currentPlayer = nextPlayer;
      nextPlayer = getNextPlayer(currentPlayer);

      Action action = currentPlayer.doTurn();
      PlayerAction playerAction = new PlayerActionImpl(currentPlayer, action);
      cardsRemaining = doAction(playerAction);
      if (state.getNumberOfLives() == 0) {
        break;
      }
    }

    // Last round, each player takes one turn.
    Player lastPlayer = currentPlayer;
    do {
      if (state.getNumberOfLives() == 0) {
        break;
      }
      currentPlayer = nextPlayer;
      nextPlayer = getNextPlayer(currentPlayer);

      Action action = currentPlayer.doFinalTurn();
      PlayerAction playerAction = new PlayerActionImpl(currentPlayer, action);
      doAction(playerAction);
    } while (currentPlayer != lastPlayer);

    return state.getScore();
  }

  private Player getNextPlayer(Player player) {
    int index = players.indexOf(player);
    int nextIndex = index + 1;
    nextIndex = (nextIndex == players.size()) ? 0 : nextIndex;
    return players.get(nextIndex);
  }

  private boolean doAction(PlayerAction playerAction) throws Exception {
    Action action = playerAction.getAction();
    switch (action.getType()) {
      case DISCARD:
        DiscardAction discardAction = (DiscardAction) action;
        discardCard(playerAction.getActingPlayer(), discardAction.getCard());
        break;
      case GIVE_CLUE:
        GiveClueAction giveClueAction = (GiveClueAction) action;
        giveClue(giveClueAction.getPlayerToClue(), giveClueAction.getClue());
        break;
      case PLAY_CARD:
        PlayCardAction playCardAction = (PlayCardAction) action;
        playCard(playerAction.getActingPlayer(), playCardAction.getCard());
        break;
    }

    for (Player player : players) {
      player.handlePlayerTakingAction(playerAction);
    }

    return state.getNumberCardsInDeck() != 0;
  }

  private void giveClue(Player playerToClue, Clue clue) throws Exception {
    playerToClue.handleClue(makePlayerClue(state.getPlayerHand(playerToClue), clue));
    if (state.getNumberOfClues() == 0) {
      throw new Exception("Cannot give clue");
    } else {
      state.clues--;
    }
  }

  private PlayerClue makePlayerClue(Hand hand, Clue clue) {
    PlayerClue playerClue = new PlayerClue(clue);
    for (Map.Entry<CardPlaceholder, CardImpl> entry : hand.cards.entrySet()) {
      if (playerClue.cardMatches(entry.getValue())) {
        playerClue.addPlaceholder(entry.getKey());
      }
    }
    return playerClue;
  }

  private void playCard(Player player, CardPlaceholder placeholder) throws Exception {
    CardImpl cardImpl = state.getPlayerHand(player).getCard(placeholder);
    state.discardCard(player, placeholder);
    if (state.board.canPlayCard(cardImpl)) {
      state.board.playCard(cardImpl);
    } else {
      state.lives--;
      state.discard.addCard(cardImpl);
    }
    drawCard(player);
  }

  private void discardCard(Player player, CardPlaceholder placeholder) {
    CardImpl cardImpl = state.getPlayerHand(player).getCard(placeholder);
    state.discardCard(player, placeholder);
    state.discard.addCard(cardImpl);
    drawCard(player);
    if (state.getNumberOfClues() < 8) {
      state.clues++;
    }
  }

  private void drawCard(Player drawingPlayer) {
    CardPlaceholder placeholder = state.drawCard(drawingPlayer);
    if (placeholder != null) {
      CardImpl card = state.getPlayerHand(drawingPlayer).getCard(placeholder);
      for (Player player : players) {
        if (player == drawingPlayer) {
          player.handleDrawingCard(placeholder);
        } else {
          player.handlePlayerDrawingCard(player, card);
        }
      }
    }
  }
}

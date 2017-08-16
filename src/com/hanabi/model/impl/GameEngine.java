package com.hanabi.model.impl;

import com.hanabi.model.facade.action.DiscardAction;
import com.hanabi.model.facade.action.GiveClueAction;
import com.hanabi.model.facade.action.PlayCardAction;
import com.hanabi.model.facade.action.PlayerAction;
import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.clue.Clue;
import com.hanabi.model.facade.player.Player;
import com.hanabi.model.facade.player.PlayerGameView;

import java.util.Collections;
import java.util.List;

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
    this.players = players;
  }

  public GameState getState() {
    return state;
  }

  public int run() throws Exception {
    for (Player player : players) {
      PlayerGameView view = new PlayerGameViewImpl(state, player);
      player.initializeWithView(view);
      player.initializeWithHand(
          Collections.unmodifiableList(state.getPlayerHand(player).getCards()));
    }

    Player currentPlayer = null;
    Player nextPlayer = players.get(0);

    // While there are still cards left in the deck, continue doing turns.
    boolean gameOver = false;
    while (!gameOver) {
      currentPlayer = nextPlayer;
      PlayerAction action = currentPlayer.doTurn();
      if (action.getActingPlayer() != currentPlayer) {
        throw new Exception("be nice...");
      }
      gameOver = doAction(action);
      nextPlayer = getNextPlayer(currentPlayer);
    }

    // Last round, each player takes one turn.
    Player lastPlayer = currentPlayer;
    do {
      if (state.getNumberOfLives() == 0) {
        break;
      }
      currentPlayer = nextPlayer;
      PlayerAction action = currentPlayer.doFinalTurn();
      nextPlayer = getNextPlayer(currentPlayer);
      doAction(action);
    } while (currentPlayer != lastPlayer);

    return state.getScore();
  }

  private Player getNextPlayer(Player player) {
    int index = players.indexOf(player);
    int nextIndex = index + 1;
    nextIndex = (nextIndex == players.size()) ? 0 : nextIndex;
    return players.get(nextIndex);
  }

  private boolean doAction(PlayerAction action) throws Exception {
    if (action instanceof DiscardAction) {
      DiscardAction discardAction = (DiscardAction) action;
      discardCard(discardAction.getActingPlayer(), discardAction.getCard());
    } else if (action instanceof GiveClueAction) {
      GiveClueAction giveClueAction = (GiveClueAction) action;
      giveClue(giveClueAction.getPlayerToClue(), giveClueAction.getClue());
    } else if (action instanceof PlayCardAction) {
      PlayCardAction playCardAction = (PlayCardAction) action;
      playCard(playCardAction.getActingPlayer(), playCardAction.getCard());
    }

    for (Player player : players) {
      player.handlePlayerTakingAction(action);
    }

    return state.isGameOver();
  }

  private void giveClue(Player playerToClue, Clue clue) throws Exception {
    if (state.getNumberOfClues() == 0) {
      throw new Exception("Cannot give clue");
    } else {
      state.clues--;
    }
  }

  private void playCard(Player player, Card card) throws Exception {
    CardImpl cardImpl = (CardImpl) card;
    state.discardCard(player, cardImpl);
    if (state.board.canPlayCard(cardImpl)) {
      state.board.playCard(cardImpl);
    } else {
      state.lives--;
      state.discard.addCard(cardImpl);
    }
    drawCard(player);
  }

  private void discardCard(Player player, Card card) {
    CardImpl cardImpl = (CardImpl) card;
    state.discardCard(player, cardImpl);
    state.discard.addCard(cardImpl);
    drawCard(player);
    if (state.getNumberOfClues() < 8) {
      state.clues++;
    }
  }

  private void drawCard(Player drawingPlayer) {
    CardImpl card = state.drawCard(drawingPlayer);
    if (card != null) {
      for (Player player : players) {
        if (player == drawingPlayer) {
          player.handleDrawingCard(card);
        } else {
          player.handlePlayerDrawingCard(player, card);
        }
      }
    }
  }
}

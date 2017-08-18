package com.hanabi.model.impl;

import com.hanabi.model.facade.action.DiscardAction;
import com.hanabi.model.facade.action.GiveClueAction;
import com.hanabi.model.facade.action.PlayCardAction;
import com.hanabi.model.facade.action.PlayerAction;
import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.clue.Clue;
import com.hanabi.model.facade.clue.ClueType;
import com.hanabi.model.facade.clue.ColorClue;
import com.hanabi.model.facade.clue.NumberClue;
import com.hanabi.model.facade.player.Player;
import com.hanabi.model.facade.player.PlayerClue;
import com.hanabi.model.facade.player.PlayerGameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
          new ArrayList<>(state.getPlayerHand(player).getPlaceholders()));
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
    playerToClue.handleClue(makePlayerClue(state.getPlayerHand(playerToClue), clue));
    if (state.getNumberOfClues() == 0) {
      throw new Exception("Cannot give clue");
    } else {
      state.clues--;
    }
  }

  private PlayerClue makePlayerClue(Hand hand, Clue clue) {
    if (clue instanceof ColorClue) {
      ColorClue colorClue = (ColorClue)clue;
      List<CardPlaceholder> matchingCards = hand
          .cards
          .entrySet()
          .stream()
          .filter(entry -> entry.getValue().getColor() == colorClue.getColor())
          .map(Map.Entry::getKey)
          .collect(Collectors.toList());
      return new PlayerClue(ClueType.COLOR, matchingCards, colorClue.getColor());
    } else if (clue instanceof NumberClue) {
      NumberClue numberClue = (NumberClue)clue;
      List<CardPlaceholder> matchingCards = hand
          .cards
          .entrySet()
          .stream()
          .filter(entry -> entry.getValue().getNumber() == numberClue.getNumber())
          .map(Map.Entry::getKey)
          .collect(Collectors.toList());
      return new PlayerClue(ClueType.NUMBER, matchingCards, numberClue.getNumber());
    } else {
      return null;
    }
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

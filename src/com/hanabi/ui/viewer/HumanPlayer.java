package com.hanabi.ui.viewer;

import com.hanabi.inference.HandInference;
import com.hanabi.model.facade.action.Action;
import com.hanabi.model.facade.action.PlayerAction;
import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.player.Player;
import com.hanabi.model.facade.player.PlayerClue;
import com.hanabi.model.facade.player.PlayerGameView;

import java.util.List;

public class HumanPlayer implements Player {

  HandInference handInference;

  @Override
  public void initializeWithView(PlayerGameView view) {
    handInference = new HandInference(view);
  }

  @Override
  public void initializeWithHand(List<CardPlaceholder> cards) {
  }

  @Override
  public Action doTurn() {
    return null;
  }

  @Override
  public Action doFinalTurn() {
    return doTurn();
  }

  @Override
  public void handlePlayerTakingAction(PlayerAction action) {
  }

  @Override
  public void handleClue(PlayerClue clue) {
  }

  @Override
  public void handlePlayerDrawingCard(Player player, Card card) {
  }

  @Override
  public void handleDrawingCard(CardPlaceholder card) {
  }
}

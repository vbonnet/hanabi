package com.hanabi.players;

import com.hanabi.model.facade.player.Player;
import com.hanabi.model.facade.action.DiscardAction;
import com.hanabi.model.facade.action.PlayerAction;
import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.player.PlayerGameView;
import com.hanabi.model.impl.GameState;

import java.util.List;

public class DiscardPlayer implements Player {

  private PlayerGameView view;
  private List<Card> myCards;

  @Override
  public void initializeWithView(PlayerGameView view) {
    this.view = view;
  }

  @Override
  public void initializeWithHand(List<Card> cards) {
    myCards = cards;
  }

  @Override
  public void handleAction(PlayerAction action) {
    // Don't care, we are simple.
  }

  @Override
  public PlayerAction doTurn() {
    DiscardAction action = new DiscardAction(this, myCards.get(0));
    return action;
  }

  @Override
  public PlayerAction doFinalTurn() {
    return doTurn();
  }
}

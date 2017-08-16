package com.hanabi.players;

import com.hanabi.model.facade.player.Player;
import com.hanabi.model.facade.action.DiscardAction;
import com.hanabi.model.facade.action.PlayerAction;
import com.hanabi.model.facade.card.Card;
import com.hanabi.model.impl.GameState;
import com.hanabi.model.impl.Hand;

import java.util.List;

public class DiscardPlayer implements Player {

  private GameState state;
  private Hand myHand;

  @Override
  public void initializeWithState(GameState state) {
    this.state = state;
    this.myHand = state.getPlayerHand(this);
  }

  @Override
  public void handleAction(PlayerAction action) {
    // Don't care, we are simple.
  }

  @Override
  public PlayerAction doTurn() {
    List<Card> myCards = myHand.getCards();
    DiscardAction action = new DiscardAction(this, myCards.get(0));
    return action;
  }

  @Override
  public PlayerAction doFinalTurn() {
    return doTurn();
  }
}

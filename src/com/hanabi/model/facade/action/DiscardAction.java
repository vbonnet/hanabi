package com.hanabi.model.facade.action;

import com.hanabi.model.facade.Player;
import com.hanabi.model.facade.card.Card;

public class DiscardAction implements PlayerAction {
  private final Player actingPlayer;
  private final Card cardToDiscard;

  public DiscardAction(Player actingPlayer, Card cardToDiscard) {
    this.actingPlayer = actingPlayer;
    this.cardToDiscard = cardToDiscard;
  }

  public Card getCard() {
    return cardToDiscard;
  }

  @Override
  public Player getActingPlayer() {
    return actingPlayer;
  }
}

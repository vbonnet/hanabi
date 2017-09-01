package com.hanabi.model.facade.action;

import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.player.Player;

public class DiscardAction implements Action {
  private final CardPlaceholder cardToDiscard;

  public DiscardAction(CardPlaceholder cardToDiscard) {
    this.cardToDiscard = cardToDiscard;
  }

  public CardPlaceholder getCard() {
    return cardToDiscard;
  }

  @Override
  public ActionType getType() {
    return ActionType.DISCARD;
  }
}

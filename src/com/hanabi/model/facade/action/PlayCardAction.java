package com.hanabi.model.facade.action;

import com.hanabi.model.facade.card.CardPlaceholder;

public class PlayCardAction implements Action {
  private final CardPlaceholder cardToPlay;

  public PlayCardAction(CardPlaceholder cardToPlay) {
    this.cardToPlay = cardToPlay;
  }

  public CardPlaceholder getCard() {
    return cardToPlay;
  }

  @Override
  public ActionType getType() {
    return ActionType.PLAY_CARD;
  }
}

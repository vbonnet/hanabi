package com.hanabi.model.facade.action;

import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.player.Player;

public class DiscardAction implements PlayerAction {
  private final Player actingPlayer;
  private final CardPlaceholder cardToDiscard;

  public DiscardAction(Player actingPlayer, CardPlaceholder cardToDiscard) {
    this.actingPlayer = actingPlayer;
    this.cardToDiscard = cardToDiscard;
  }

  public CardPlaceholder getCard() {
    return cardToDiscard;
  }

  @Override
  public Player getActingPlayer() {
    return actingPlayer;
  }
}

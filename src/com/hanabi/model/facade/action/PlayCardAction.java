package com.hanabi.model.facade.action;

import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.player.Player;

public class PlayCardAction implements PlayerAction {
  private final Player actingPlayer;
  private final CardPlaceholder cardToPlay;

  public PlayCardAction(Player actingPlayer, CardPlaceholder cardToPlay) {
    this.actingPlayer = actingPlayer;
    this.cardToPlay = cardToPlay;
  }

  public CardPlaceholder getCard() {
    return cardToPlay;
  }

  @Override
  public Player getActingPlayer() {
    return actingPlayer;
  }
}

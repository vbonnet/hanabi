package com.hanabi.model.facade.action;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.player.Player;

public class PlayCardAction implements PlayerAction {
  private final Player actingPlayer;
  private final Card cardToPlay;

  public PlayCardAction(Player actingPlayer, Card cardToPlay) {
    this.actingPlayer = actingPlayer;
    this.cardToPlay = cardToPlay;
  }

  public Card getCard() {
    return cardToPlay;
  }

  @Override
  public Player getActingPlayer() {
    return actingPlayer;
  }
}

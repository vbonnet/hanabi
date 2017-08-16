package com.hanabi.model.facade.action;

import com.hanabi.model.facade.clue.Clue;
import com.hanabi.model.facade.player.Player;

public class GiveClueAction implements PlayerAction {
  private final Player actingPlayer;
  private final Player playerToClue;
  private final Clue clue;

  public GiveClueAction(Player actingPlayer, Player playerToClue, Clue clue) {
    this.actingPlayer = actingPlayer;
    this.playerToClue = playerToClue;
    this.clue = clue;
  }

  public Clue getClue() {
    return clue;
  }

  public Player getPlayerToClue() {
    return playerToClue;
  }

  @Override
  public Player getActingPlayer() {
    return actingPlayer;
  }
}

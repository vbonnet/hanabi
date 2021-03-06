package com.hanabi.model.facade.action;

import com.hanabi.model.facade.clue.Clue;
import com.hanabi.model.facade.player.Player;

public class GiveClueAction implements Action {
  private final Player playerToClue;
  private final Clue clue;

  public GiveClueAction(Player playerToClue, Clue clue) {
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
  public ActionType getType() {
    return ActionType.GIVE_CLUE;
  }
}

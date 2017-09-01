package com.hanabi.model.impl;

import com.hanabi.model.facade.action.Action;
import com.hanabi.model.facade.action.PlayerAction;
import com.hanabi.model.facade.player.Player;

public class PlayerActionImpl implements PlayerAction {

  final Player actingPlayer;
  final Action action;

  PlayerActionImpl(Player actingPlayer, Action action) {
    this.actingPlayer = actingPlayer;
    this.action = action;
  }

  @Override
  public Player getActingPlayer() {
    return actingPlayer;
  }

  @Override
  public Action getAction() {
    return action;
  }
}

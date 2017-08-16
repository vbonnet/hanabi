package com.hanabi.model.impl;

import com.hanabi.model.facade.player.Player;
import com.hanabi.model.facade.player.PlayerGameView;

public class PlayerGameViewImpl implements PlayerGameView {

  private Player player;
  private GameState state;

  protected PlayerGameViewImpl(GameState state, Player player) {
    this.player = player;
    this.state = state;
  }
}

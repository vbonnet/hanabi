package com.hanabi.model.facade.action;

import com.hanabi.model.facade.player.Player;

public interface PlayerAction {
  Player getActingPlayer();
  Action getAction();
}


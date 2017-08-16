package com.hanabi.model.facade.player;

import com.hanabi.model.facade.action.PlayerAction;
import com.hanabi.model.impl.GameState;

public interface Player {
  void initializeWithState(GameState state);
  void handleAction(PlayerAction action);
  PlayerAction doTurn();
  PlayerAction doFinalTurn();
}

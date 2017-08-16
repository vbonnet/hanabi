package com.hanabi.model.facade.player;

import com.hanabi.model.facade.action.PlayerAction;
import com.hanabi.model.facade.card.Card;

import java.util.List;

public interface Player {
  void initializeWithView(PlayerGameView view);
  void initializeWithHand(List<Card> cards);

  void handleAction(PlayerAction action);
  PlayerAction doTurn();
  PlayerAction doFinalTurn();
}

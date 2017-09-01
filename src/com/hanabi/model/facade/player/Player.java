package com.hanabi.model.facade.player;

import com.hanabi.model.facade.action.Action;
import com.hanabi.model.facade.action.PlayerAction;
import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.CardPlaceholder;

import java.util.List;

public interface Player {
  void initializeWithView(PlayerGameView view);

  void initializeWithHand(List<CardPlaceholder> cards);

  Action doTurn();

  Action doFinalTurn();

  void handlePlayerTakingAction(PlayerAction action);

  void handleClue(PlayerClue clue);

  void handlePlayerDrawingCard(Player player, Card card);

  void handleDrawingCard(CardPlaceholder card);

}

package com.hanabi.model.facade.player;

import com.hanabi.model.facade.action.PlayerAction;
import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.card.RevealedCard;

import java.util.List;

public interface Player {
  void initializeWithView(PlayerGameView view);

  void initializeWithHand(List<CardPlaceholder> cards);

  PlayerAction doTurn();

  PlayerAction doFinalTurn();

  void handlePlayerTakingAction(PlayerAction action);

  void handleClue(PlayerClue clue);

  void handlePlayerDrawingCard(Player player, RevealedCard card);

  void handleDrawingCard(CardPlaceholder card);

}

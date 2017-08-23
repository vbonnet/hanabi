package com.hanabi.players;

import com.hanabi.model.facade.action.DiscardAction;
import com.hanabi.model.facade.action.PlayerAction;
import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.player.Player;
import com.hanabi.model.facade.player.PlayerClue;
import com.hanabi.model.facade.player.PlayerGameView;

import java.util.List;

public class DiscardPlayer implements Player {

  private List<CardPlaceholder> myCards;

  @Override
  public void initializeWithView(PlayerGameView view) {
  }

  @Override
  public void initializeWithHand(List<CardPlaceholder> cards) {
    myCards = cards;
  }

  @Override
  public PlayerAction doTurn() {
    return new DiscardAction(this, myCards.get(0));
  }

  @Override
  public PlayerAction doFinalTurn() {
    return doTurn();
  }

  @Override
  public void handlePlayerTakingAction(PlayerAction action) {
  }

  @Override
  public void handleClue(PlayerClue clue) {
  }

  @Override
  public void handlePlayerDrawingCard(Player player, Card card) {
  }

  @Override
  public void handleDrawingCard(CardPlaceholder card) {
  }
}

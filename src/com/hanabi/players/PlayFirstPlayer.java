package com.hanabi.players;

import com.hanabi.model.facade.action.PlayCardAction;
import com.hanabi.model.facade.action.PlayerAction;
import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.card.RevealedCard;
import com.hanabi.model.facade.player.Player;
import com.hanabi.model.facade.player.PlayerClue;
import com.hanabi.model.facade.player.PlayerGameView;

import java.util.List;

public class PlayFirstPlayer implements Player {

  private PlayerGameView view;

  @Override
  public void initializeWithView(PlayerGameView view) {
    this.view = view;
  }

  @Override
  public void initializeWithHand(List<CardPlaceholder> cards) {}

  @Override
  public PlayerAction doTurn() {
    return new PlayCardAction(this, view.getHand().get(0));
  }

  @Override
  public PlayerAction doFinalTurn() {
    return doTurn();
  }


  @Override
  public void handlePlayerTakingAction(PlayerAction action) {}

  @Override
  public void handleClue(PlayerClue clue) {}

  @Override
  public void handlePlayerDrawingCard(Player player, RevealedCard card) {}

  @Override
  public void handleDrawingCard(CardPlaceholder card) {}
}

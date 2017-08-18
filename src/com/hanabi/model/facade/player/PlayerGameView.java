package com.hanabi.model.facade.player;

import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.card.CardColor;
import com.hanabi.model.facade.card.Card;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface PlayerGameView {
  int getSizeOfDeck();
  int getNumberOfClues();
  int getNumberOfLives();

  List<CardPlaceholder> getHand();
  Collection<Card> getAllCards();
  Collection<Card> getDiscard();
  Collection<Card> getPlayerHand(Player player) throws Exception;

  Collection<Player> getOtherPlayers();

  Map<CardColor, Integer> getPlayStacks();
}

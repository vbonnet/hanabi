package com.hanabi.model.facade.player;

import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.card.CardColor;
import com.hanabi.model.facade.card.Card;

import java.util.List;
import java.util.Map;

public interface PlayerGameView {
  int getSizeOfDeck();
  int getNumberOfClues();
  int getNumberOfLives();

  List<CardPlaceholder> getHand();
  List<Card> getAllCards();
  List<Card> getDiscard();
  List<Card> getPlayerHand(Player player) throws Exception;

  List<Player> getOtherPlayers();

  Map<CardColor, Integer> getPlayStacks();
}

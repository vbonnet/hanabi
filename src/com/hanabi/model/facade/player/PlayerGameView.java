package com.hanabi.model.facade.player;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.Color;
import com.hanabi.model.facade.card.RevealedCard;

import java.util.Collection;
import java.util.Map;

public interface PlayerGameView {
  int getSizeOfDeck();
  int getNumberOfClues();
  int getNumberOfLives();

  Collection<Card> getHand();
  Collection<RevealedCard> getAllCards();
  Collection<RevealedCard> getDiscard();
  Collection<RevealedCard> getPlayerHand(Player player) throws Exception;

  Collection<Player> getOtherPlayers();

  Map<Color, Integer> getPlayStacks();
}

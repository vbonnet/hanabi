package com.hanabi.model.facade.player;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.RevealedCard;

import java.util.Collection;

public interface PlayerGameView {
  int getSizeOfDeck();
  int getNumberOfClues();
  int getNumberOfLives();

  Collection<Card> getHand();
  Collection<Card> getAllCards();
  Collection<RevealedCard> getDiscard();
  Collection<RevealedCard> getPlayerHand(Player player) throws Exception;
}

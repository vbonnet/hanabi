package com.hanabi.com.hanabi.deducer;

import com.hanabi.model.facade.card.CardColor;
import com.hanabi.model.facade.card.Card;
import com.hanabi.util.MapCounter;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CardInference implements  CardCounterListener {
  private MapCounter<Card> possibleCards = new MapCounter<>();
  private CardCounter cardCounter;

  public CardInference(CardCounter cardCounter) {
    this.cardCounter = cardCounter;
    for (Card card : cardCounter.getCards()) {
      possibleCards.increment(card);
    }
    cardCounter.addListener(this);
  }

  public boolean isKnown() {
    return possibleCards.size() == 1;
  }

  public Collection<Card> getPossibleCards() {
    return possibleCards.keySet();
  }

  public Set<CardColor> getPossibleColors() {
    return possibleCards
        .keySet()
        .stream()
        .map(Card::getColor)
        .collect(Collectors.toSet());
  }

  public Set<Integer> getPossibleNumbers() {
    return possibleCards
        .keySet()
        .stream()
        .map(Card::getNumber)
        .collect(Collectors.toSet());
  }

  public void setColor(CardColor color) {
    possibleCards.removeIf(card -> card.getColor() != color);
    updateInference();
  }

  public void removeColor(CardColor color) {
    possibleCards.removeIf(card -> card.getColor() == color);
    updateInference();
  }

  public void setNumber(Integer number) {
    possibleCards.removeIf(card -> card.getNumber() != number);
    updateInference();
  }

  public void removeNumber(Integer number) {
    possibleCards.removeIf(card -> card.getNumber() == number);
    updateInference();
  }

  public void setValue(Object object ) {
    if (object instanceof CardColor) {
      setColor((CardColor)object);
    } else if (object instanceof Integer) {
      setNumber((Integer)object);
    }
  }

  public void removeValue(Object object) {
    if (object instanceof CardColor) {
      removeColor((CardColor)object);
    } else if (object instanceof Integer) {
      removeNumber((Integer)object);
    }
  }

  private void updateInference() {
    if (possibleCards.keySet().size() == 1) {
      Card card = possibleCards.keySet().iterator().next();
      cardCounter.remove(card, this);
    }
  }

  @Override
  public void handleCardRemoved(Card card) {
    possibleCards.decrement(card);
  }
}

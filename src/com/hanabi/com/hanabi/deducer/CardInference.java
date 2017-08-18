package com.hanabi.com.hanabi.deducer;

import com.hanabi.model.facade.card.CardColor;
import com.hanabi.model.facade.card.Card;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CardInference implements  CardCounterListener {
  private Collection<Card> possibleCards;

  CardInference(CardCounter cardCounter) {
    this.possibleCards = cardCounter.getCards();
    cardCounter.addListener(this);
  }

  public boolean isKnown() {
    if (possibleCards.size() == 1) {
      return true;
    } else {
      // It's possible we know exactly what the card is but that there's multiple copies
      // of that card still ou there. We still know it though.
      return getPossibleColors().size() == 1 && getPossibleNumbers().size() == 1;
    }
  }

  public Collection<Card> getPossibleCards() {
    return possibleCards;
  }

  public Set<CardColor> getPossibleColors() {
    return possibleCards
        .stream()
        .map(card -> card.getColor())
        .collect(Collectors.toSet());
  }

  public Set<Integer> getPossibleNumbers() {
    return possibleCards
        .stream()
        .map(card -> card.getNumber())
        .collect(Collectors.toSet());
  }

  public void setColor(CardColor color) {
    possibleCards.removeIf(card -> card.getColor() != color);
  }

  public void removeColor(CardColor color) {
    possibleCards.removeIf(card -> card.getColor() == color);
  }

  public void setNumber(Integer number) {
    possibleCards.removeIf(card -> card.getNumber() != number);
  }

  public void removeNumber(Integer number) {
    possibleCards.removeIf(card -> card.getNumber() == number);

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

  @Override
  public void handleCardRemoved(Card card) {
    possibleCards.remove(card);
  }
}

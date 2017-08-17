package com.hanabi.com.hanabi.deducer;

import com.hanabi.model.facade.card.Color;
import com.hanabi.model.facade.card.RevealedCard;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CardInference {
  private Collection<RevealedCard> possibleCards;

  CardInference(CardCounter cardCounter) {
    this.possibleCards = cardCounter.getCards();
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

  public Collection<RevealedCard> getPossibleCards() {
    return possibleCards;
  }

  public Set<Color> getPossibleColors() {
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

  public void setColor(Color color) {
    possibleCards.removeIf(card -> card.getColor() != color);
  }

  public void removeColor(Color color) {
    possibleCards.removeIf(card -> card.getColor() == color);
  }

  public void setNumber(Integer number) {
    possibleCards.removeIf(card -> card.getNumber() != number);
  }

  public void removeNumber(Integer number) {
    possibleCards.removeIf(card -> card.getNumber() == number);

  }

  public void setValue(Object object ) {
    if (object instanceof Color) {
      setColor((Color)object);
    } else if (object instanceof Integer) {
      setNumber((Integer)object);
    }
  }

  public void removeValue(Object object) {
    if (object instanceof Color) {
      removeColor((Color)object);
    } else if (object instanceof Integer) {
      removeNumber((Integer)object);
    }
  }
}

package com.hanabi.com.hanabi.deducer;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.Color;
import com.hanabi.model.facade.card.RevealedCard;

import java.util.HashSet;
import java.util.Set;

public class CardInference {
  private CardCounter cardCounter;
  private Set<Color> possibleColors;
  private Set<Integer> possibleNumbers;

  CardInference() {
    this(null);
  }

  CardInference(CardCounter cardCounter) {
    this.cardCounter = cardCounter;
    possibleColors = new HashSet<>();
    for (Color color: Color.values()) {
      possibleColors.add(color);
    }
    possibleNumbers = new HashSet<>();
    for (int i = 1; i <= 5; i++) {
      possibleNumbers.add(i);
    }
  }

  public boolean isKnown() {
    return possibleColors.size() == 1 && possibleNumbers.size() == 1;
  }

  public Set<Color> getPossibleColors() {
    return possibleColors;
  }

  public Set<Integer> getPossibleNumbers() {
    return possibleNumbers;
  }
  public void setColor(Color color) {
    possibleColors.clear();
    possibleColors.add(color);
    inferNumber(color);
  }

  public void removeColor(Color color) {
    possibleColors.remove(color);
    if (possibleColors.size() == 1) {
      inferNumber(possibleColors.iterator().next());
    }
  }

  public void setNumber(Integer number) {
    possibleNumbers.clear();
    possibleNumbers.add(number);
  }

  public void removeNumber(Integer number) {
    possibleNumbers.remove(number);
    if (possibleNumbers.size() == 1) {
      inferColor(possibleNumbers.iterator().next());
    }
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

  private void inferNumber(Color color) {
    if (cardCounter == null) {
      return;
    }
    if (isKnown()) {
      // no need to waste time making inferences.
      return;
    }

    // Remove any numbers that don't show up in the remaining cards with that color.
    Set<Integer> remainingNumbers = new HashSet<>();
    for (RevealedCard card : cardCounter.getCardsByColor(color)) {
      remainingNumbers.add(card.getValue());
    }
    possibleNumbers.retainAll(remainingNumbers);
  }

  private void inferColor(Integer number) {
    if (cardCounter == null) {
      return;
    }
    if (isKnown()) {
      // no need to waste time making inferences.
      return;
    }

    // Remove any colors that don't show up in the remaining cards with that number.
    Set<Color> remainingColors = new HashSet<>();
    for (RevealedCard card : cardCounter.getCardsByNumber(number)) {
      remainingColors.add(card.getColor());
    }
    possibleColors.retainAll(remainingColors);
  }
}

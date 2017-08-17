package com.hanabi.com.hanabi.deducer;

import com.hanabi.model.facade.card.Color;

import java.util.HashSet;
import java.util.Set;

public class CardInference {
  private Set<Color> possibleColors;
  private Set<Integer> possibleNumbers;

  CardInference() {
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

  public Set<Color> getPosibleColors() {
    return possibleColors;
  }

  public Set<Integer> getPossibleNumbers() {
    return possibleNumbers;
  }
  public void setColor(Color color) {
    possibleColors.clear();
    possibleColors.add(color);
  }

  public void removeColor(Color color) {
    possibleColors.remove(color);
  }

  public void setNumber(Integer number) {
    possibleNumbers.clear();
    possibleNumbers.add(number);
  }

  public void removeNumber(Integer number) {
    possibleNumbers.remove(number);
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

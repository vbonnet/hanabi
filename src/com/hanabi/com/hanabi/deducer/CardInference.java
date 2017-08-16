package com.hanabi.com.hanabi.deducer;

import com.hanabi.model.facade.card.Color;

import java.util.HashSet;
import java.util.Set;

public class CardInference {
  private Set<Color> possibeColors;
  private Set<Integer> possibleNumbers;

  CardInference() {
    possibeColors = new HashSet<>();
    for (Color color: Color.values()) {
      possibeColors.add(color);
    }
    possibleNumbers = new HashSet<>();
    for (int i = 1; i <= 5; i++) {
      possibleNumbers.add(i);
    }
  }

  public boolean isKnown() {
    return possibeColors.size() == 1 && possibleNumbers.size() == 1;
  }

  public void setColor(Color color) {
    possibeColors.clear();
    possibeColors.add(color);
  }

  public void removeColor(Color color) {
    possibeColors.remove(color);
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

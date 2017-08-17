package com.hanabi.model.impl;

import com.hanabi.model.facade.card.Color;

import java.util.HashMap;
import java.util.Map;

class Board {
   final Map<Color, Integer> stacks;

  Board() {
    stacks = new HashMap<>();
    for (Color color : Color.values()) {
      stacks.put(color, 0);
    }
  }

  int getScore() {
    int score = 0;
    for (Integer stackScore : stacks.values()) {
      score += stackScore;
    }
    return score;
  }

  boolean canPlayCard(CardImpl card) {
    Integer currentValue = stacks.get(card.getColor());
    return card.getValue() == currentValue + 1;
  }

  void playCard(CardImpl card) throws Exception {
    if (canPlayCard(card)) {
      stacks.put(card.getColor(), card.getValue());
    } else {
      throw new Exception("Cannot play that card");
    }
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Map.Entry<Color, Integer> entry : stacks.entrySet()) {
      s.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
    }
    return s.toString();
  }
}

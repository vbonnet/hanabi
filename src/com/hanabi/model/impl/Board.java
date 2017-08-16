package com.hanabi.model.impl;

import com.hanabi.model.facade.card.Color;

import java.util.HashMap;
import java.util.Map;

public class Board {
  private final Map<Color, Integer> stacks;

  protected Board() {
    stacks = new HashMap<>();
    for (Color color : Color.values()) {
      stacks.put(color, 0);
    }
  }

  protected int getScore() {
    int score = 0;
    for (Integer stackScore : stacks.values()) {
      score += stackScore;
    }
    return score;
  }

  protected boolean canPlayCard(CardImpl card) {
    Integer currentValue = stacks.get(card.getColor());
    return card.getValue() == currentValue + 1;
  }

  protected void playCard(CardImpl card) throws Exception {
    if (canPlayCard(card)) {
      stacks.put(card.getColor(), card.getValue());
    } else {
      throw new Exception("Cannot play that card");
    }
  }

  @Override
  public String toString() {
    String s = "";
    for (Map.Entry<Color, Integer> entry : stacks.entrySet()) {
      s += entry.getKey() + ": " + entry.getValue() + "\n";
    }
    return s;
  }
}

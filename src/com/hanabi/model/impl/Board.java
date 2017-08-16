package com.hanabi.model.impl;

import com.hanabi.model.facade.card.Color;

import java.util.HashMap;
import java.util.Map;

public class Board {
  private Map<Color, Integer> stacks;

  protected Board() {
    stacks = new HashMap<>();
    for (Color color : Color.values()) {
      stacks.put(color, 0);
    }
  }

  public int getScore() {
    int score = 0;
    for (Integer stackScore : stacks.values()) {
      score += stackScore;
    }
    return score;
  }

  public boolean canPlayCard(CardImpl card) {
    Integer currentValue = stacks.get(card.color);
    return card.value == currentValue + 1;
  }

  protected void playCard(CardImpl card) throws Exception {
    if (canPlayCard(card)) {
      stacks.put(card.color, card.value);
    } else {
      throw new Exception("Cannot play that card");
    }
  }

  @Override
  public String toString() {
    String s = "";
    for (Map.Entry<Color, Integer> entry : stacks.entrySet()) {
      s += entry.getKey() +  ": " + entry.getValue() + "\n";
    }
    return s;
  }
}

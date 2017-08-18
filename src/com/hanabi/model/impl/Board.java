package com.hanabi.model.impl;

import com.hanabi.model.facade.card.CardColor;

import java.util.HashMap;
import java.util.Map;

class Board {
   final Map<CardColor, Integer> stacks;

  Board() {
    stacks = new HashMap<>();
    for (CardColor color : CardColor.values()) {
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
    return card.getNumber() == currentValue + 1;
  }

  void playCard(CardImpl card) throws Exception {
    if (canPlayCard(card)) {
      stacks.put(card.getColor(), card.getNumber());
    } else {
      throw new Exception("Cannot play that card");
    }
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Map.Entry<CardColor, Integer> entry : stacks.entrySet()) {
      s.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
    }
    return s.toString();
  }
}

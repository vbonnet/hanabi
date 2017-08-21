package com.hanabi.model.impl;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.CardColor;

import java.util.HashMap;
import java.util.Map;

class Board {
   final Map<CardColor, Card> stacks;

  Board() {
    stacks = new HashMap<>();
    for (CardColor color : CardColor.values()) {
      stacks.put(color, null);
    }
  }

  int getScore() {
    int score = 0;
    for (Card card : stacks.values()) {
      score += card == null ? 0 : card.getNumber();
    }
    return score;
  }

  boolean canPlayCard(CardImpl card) {
    Integer currentValue = stacks.get(card.getColor()).getNumber();
    return card.getNumber() == currentValue + 1;
  }

  void playCard(CardImpl card) throws Exception {
    if (canPlayCard(card)) {
      stacks.put(card.getColor(), card);
    } else {
      throw new Exception("Cannot play that card");
    }
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Map.Entry<CardColor, Card> entry : stacks.entrySet()) {
      s.append(entry.getKey()).append(": ").append(entry.getValue().getNumber()).append("\n");
    }
    return s.toString();
  }
}

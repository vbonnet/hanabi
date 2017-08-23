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
      stacks.put(color, new CardImpl(0, color));
    }
  }

  int getScore() {
    int score = 0;
    for (Card card : stacks.values()) {
      score += getStackValue(card.getColor());
    }
    return score;
  }

  boolean canPlayCard(CardImpl card) {
    Integer currentValue = getStackValue(card.getColor());
    return card.getNumber() == currentValue + 1;
  }

  void playCard(CardImpl card) throws Exception {
    if (canPlayCard(card)) {
      stacks.put(card.getColor(), card);
    } else {
      throw new Exception("Cannot play that card");
    }
  }

  private int getStackValue(CardColor color) {
    return stacks.get(color).getNumber();
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

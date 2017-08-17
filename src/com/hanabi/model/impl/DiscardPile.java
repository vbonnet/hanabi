package com.hanabi.model.impl;

import com.hanabi.model.facade.card.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class DiscardPile {
  private final List<CardImpl> cards = new ArrayList<>();

  List<CardImpl> getCards() {
    return cards;
  }

  void addCard(CardImpl card) {
    cards.add(card);
  }

  protected List<CardImpl> getCardByColor(Color color) {
    return cards.stream().filter(card -> card.getColor() == color).collect(Collectors.toList());
  }

  protected List<CardImpl> getCardByNumber(int value) {
    return cards.stream().filter(card -> card.getNumber() == value).collect(Collectors.toList());
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (CardImpl card : cards) {
      s.append(card.toString()).append("\n");
    }
    return s.toString();
  }
}

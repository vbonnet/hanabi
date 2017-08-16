package com.hanabi.model.impl;

import com.hanabi.model.facade.card.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiscardPile {
  private final List<CardImpl> cards = new ArrayList<>();

  public List<CardImpl> getCards() {
    return cards;
  }

  protected void addCard(CardImpl card) {
    cards.add(card);
  }

  public List<CardImpl> getCardByColor(Color color) {
    return cards.stream().filter(card -> card.color == color).collect(Collectors.toList());
  }

  public List<CardImpl> getCardByNumber(int value) {
    return cards.stream().filter(card -> card.value == value).collect(Collectors.toList());
  }

  @Override
  public String toString() {
    String s = "";
    for (CardImpl card : cards) {
      s += card.toString() + "\n";
    }
    return s;
  }
}

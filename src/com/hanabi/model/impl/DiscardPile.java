package com.hanabi.model.impl;

import com.hanabi.model.facade.card.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiscardPile {
  private final List<CardImpl> cards = new ArrayList<>();

  protected List<CardImpl> getCards() {
    return cards;
  }

  protected void addCard(CardImpl card) {
    cards.add(card);
  }

  protected List<CardImpl> getCardByColor(Color color) {
    return cards.stream().filter(card -> card.getColor() == color).collect(Collectors.toList());
  }

  protected List<CardImpl> getCardByNumber(int value) {
    return cards.stream().filter(card -> card.getValue() == value).collect(Collectors.toList());
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
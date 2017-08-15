package com.hanabi.model.impl;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiscardPile {
  private final List<Card> cards = new ArrayList<>();

  public List<Card> getCards() {
    return cards;
  }

  protected void addCard(Card card) {
    cards.add(card);
  }

  public List<Card> getCardByColor(Color color) {
    return cards.stream().filter(card -> card.color == color).collect(Collectors.toList());
  }

  public List<Card> getCardByNumber(int value) {
    return cards.stream().filter(card -> card.value == value).collect(Collectors.toList());
  }

  @Override
  public String toString() {
    String s = "";
    for (Card card : cards) {
      s += card.toString() + "\n";
    }
    return s;
  }
}

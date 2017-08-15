package com.hanabi.model.impl;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.PlayerHand;

import java.util.ArrayList;
import java.util.List;

public class Hand implements PlayerHand {
  List<Card> cards = new ArrayList<>();

  @Override
  public List<Card> getCards() {
    return cards;
  }

  protected void removeCard(Card card) {
    cards.remove(card);
  }

  protected void addCard(Card card) {
    cards.add(card);
  }
}

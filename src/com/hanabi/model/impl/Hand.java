package com.hanabi.model.impl;

import com.hanabi.model.facade.PlayerHand;
import com.hanabi.model.facade.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand implements PlayerHand {
  private List<CardImpl> cards = new ArrayList<>();

  @Override
  public List<Card> getCards() {
    return new ArrayList<>(cards);
  }

  protected void removeCard(CardImpl card) {
    cards.remove(card);
  }

  protected void addCard(CardImpl card) {
    cards.add(card);
  }
}

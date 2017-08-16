package com.hanabi.model.impl;

import com.hanabi.model.facade.player.PlayerHand;

import java.util.ArrayList;
import java.util.List;

public class Hand implements PlayerHand {
  private final List<CardImpl> cards = new ArrayList<>();

  @Override
  public List<CardImpl> getCards() {
    return new ArrayList<>(cards);
  }

  void removeCard(CardImpl card) {
    cards.remove(card);
  }

  void addCard(CardImpl card) {
    cards.add(card);
  }
}

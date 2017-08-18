package com.hanabi.model.impl;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.player.PlayerHand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Hand implements PlayerHand {
  final HashMap<CardPlaceholder, CardImpl> cards = new HashMap<>();

  @Override
  public List<Card> getCards() {
    return new ArrayList<>(cards.values());
  }

  Collection<CardPlaceholder> getPlaceholders() {
    return cards.keySet();
  }

  CardImpl getCard(CardPlaceholder placeholder) {
    return cards.get(placeholder);
  }

  void removeCard(CardPlaceholder placeholder) {
    cards.remove(placeholder);
  }

  CardPlaceholder addCard(CardImpl card) {
    CardPlaceholder placeholder = new CardPlaceholder();
    cards.put(placeholder, card);
    return placeholder;
  }
}

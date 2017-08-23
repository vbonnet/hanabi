package com.hanabi.model.impl;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.player.PlayerHand;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class Hand implements PlayerHand {
  final LinkedHashMap<CardPlaceholder, CardImpl> cards = new LinkedHashMap<>();

  @Override
  public List<Card> getCards() {
    return new ArrayList<>(cards.values());
  }

  public Set<CardPlaceholder> getPlaceholders() {
    return cards.keySet();
  }

  public CardImpl getCard(CardPlaceholder placeholder) {
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

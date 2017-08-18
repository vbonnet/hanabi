package com.hanabi.com.hanabi.deducer;

import com.hanabi.model.facade.card.Color;
import com.hanabi.model.facade.card.Card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public class CardCounter {
  Collection<Card> remainingCards;
  final HashSet<CardCounterListener> listeners = new HashSet<>();

  CardCounter(Collection<Card> cards) {
    this.remainingCards = cards;
  }

  public void remove(Card card) {
    remainingCards.remove(card);
    for (CardCounterListener listener : listeners) {
      listener.handleCardRemoved(card);
    }
  }

  public Collection<Card> getCards() {
    return new ArrayList<>(remainingCards);
  }

  public Collection<Card> getCardsByColor(Color color) {
    return remainingCards
        .stream()
        .filter(c -> c.getColor() == color)
        .collect(Collectors.toList());
  }

  public Collection<Card> getCardsByNumber(Integer number) {
    return remainingCards
        .stream()
        .filter(c -> c.getNumber() == number)
        .collect(Collectors.toList());
  }

  public Collection<Card> getCardsByColorAndNumber(Color color, Integer number) {
    return remainingCards
        .stream()
        .filter(c -> c.getColor() == color && c.getNumber() == number)
        .collect(Collectors.toList());
  }

  public void addListener(CardCounterListener listener) {
    listeners.add(listener);
  }

  public void removeListener(CardCounterListener listener) {
    listeners.remove(listener);
  }
}

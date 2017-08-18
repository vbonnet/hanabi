package com.hanabi.com.hanabi.deducer;

import com.hanabi.model.facade.card.Color;
import com.hanabi.model.facade.card.RevealedCard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public class CardCounter {
  Collection<RevealedCard> remainingCards;
  final HashSet<CardCounterListener> listeners = new HashSet<>();

  CardCounter(Collection<RevealedCard> cards) {
    this.remainingCards = cards;
  }

  public void remove(RevealedCard card) {
    remainingCards.remove(card);
    for (CardCounterListener listener : listeners) {
      listener.handleCardRemoved(card);
    }
  }

  public Collection<RevealedCard> getCards() {
    return new ArrayList<>(remainingCards);
  }

  public Collection<RevealedCard> getCardsByColor(Color color) {
    return remainingCards
        .stream()
        .filter(c -> c.getColor() == color)
        .collect(Collectors.toList());
  }

  public Collection<RevealedCard> getCardsByNumber(Integer number) {
    return remainingCards
        .stream()
        .filter(c -> c.getNumber() == number)
        .collect(Collectors.toList());
  }

  public Collection<RevealedCard> getCardsByColorAndNumber(Color color, Integer number) {
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

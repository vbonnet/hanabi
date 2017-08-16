package com.hanabi.model.impl;

import com.hanabi.model.facade.card.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
  private final List<CardImpl> cards;

  protected Deck(List<CardImpl> cards) {
    this.cards = new ArrayList<>(cards);
  }

  protected Deck() {
    List<CardImpl> cardList = fullCardList();
    Collections.shuffle(cardList);
    this.cards = cardList;
  }

  public static List<CardImpl> fullCardList() {
    ArrayList<CardImpl> cardList = new ArrayList<>();
    for (Color color : Color.values()) {
      cardList.add(new CardImpl(1, color));
      cardList.add(new CardImpl(1, color));
      cardList.add(new CardImpl(1, color));

      cardList.add(new CardImpl(2, color));
      cardList.add(new CardImpl(2, color));

      cardList.add(new CardImpl(3, color));
      cardList.add(new CardImpl(3, color));

      cardList.add(new CardImpl(4, color));
      cardList.add(new CardImpl(4, color));

      cardList.add(new CardImpl(5, color));
    }
    return cardList;
  }

  protected CardImpl getNextCard() {
    if (cards.size() == 0) {
      return null;
    }
    CardImpl topCard = cards.get(0);
    cards.remove(topCard);
    return topCard;
  }

  public int getNumberCards() {
    return cards.size();
  }
}
package com.hanabi.model.impl;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
  private List<Card> cards;

  public static List<Card> fullCardList() {
    ArrayList<Card> cardList = new ArrayList<>();
    for (Color color : Color.values()) {
      cardList.add(new Card(1, color));
      cardList.add(new Card(1, color));
      cardList.add(new Card(1, color));

      cardList.add(new Card(2, color));
      cardList.add(new Card(2, color));

      cardList.add(new Card(3, color));
      cardList.add(new Card(3, color));

      cardList.add(new Card(4, color));
      cardList.add(new Card(4, color));

      cardList.add(new Card(5, color));
    }
    return cardList;
  }

  public Deck(List<Card> cards) {
    this.cards = new ArrayList<>(cards);
  }

  public Deck() {
    List<Card> cardList = fullCardList();
    Collections.shuffle(cardList);
    this.cards = cardList;
  }

  protected Card getNextCard() {
    if (cards.size() == 0) {
      return null;
    }
    Card topCard = cards.get(0);
    cards.remove(topCard);
    return topCard;
  }

  public int getNumberCards() {
    return cards.size();
  }
}

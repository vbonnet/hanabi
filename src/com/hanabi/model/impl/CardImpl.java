package com.hanabi.model.impl;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.CardColor;

public class CardImpl implements Card {
  private final int value;
  private final CardColor color;

  CardImpl(int value, CardColor color) {
    this.value = value;
    this.color = color;
  }

  @Override
  public CardColor getColor() {
    return color;
  }

  @Override
  public Integer getNumber() {
    return value;
  }

  @Override
  public String toString() {
    return "(" + value + ", " + color.toString() + ")";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Card) {
      Card otherCard = (Card)obj;
      return (
          otherCard.getColor() == this.getColor() &&
          otherCard.getNumber() == this.getNumber());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }
}

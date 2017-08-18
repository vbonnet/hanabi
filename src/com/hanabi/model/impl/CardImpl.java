package com.hanabi.model.impl;

import com.hanabi.model.facade.card.CardColor;
import com.hanabi.model.facade.card.Card;

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
  public int getNumber() {
    return value;
  }

  @Override
  public String toString() {
    return "(" + value + ", " + color.toString() + ")";
  }
}

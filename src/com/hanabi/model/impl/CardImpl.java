package com.hanabi.model.impl;

import com.hanabi.model.facade.card.Color;
import com.hanabi.model.facade.card.RevealedCard;

public class CardImpl implements RevealedCard {
  private final int value;
  private final Color color;

  CardImpl(int value, Color color) {
    this.value = value;
    this.color = color;
  }

  @Override
  public Color getColor() {
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

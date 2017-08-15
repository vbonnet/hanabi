package com.hanabi.model.impl;

import com.hanabi.model.facade.Color;
import com.hanabi.model.facade.card.RevealedCard;

public class CardImpl implements RevealedCard {
  public final int value;
  public final Color color;

  public CardImpl(int value, Color color) {
    this.value = value;
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "(" + value + ", " + color.toString() + ")";
  }
}

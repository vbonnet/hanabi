package com.hanabi.model.facade.card;

import com.hanabi.model.facade.Color;

public class Card {
  public final int value;
  public final Color color;

  public Card(int value, Color color) {
    this.value = value;
    this.color = color;
  }

  @Override
  public String toString() {
    return "(" + value + ", " + color.toString() + ")";
  }
}

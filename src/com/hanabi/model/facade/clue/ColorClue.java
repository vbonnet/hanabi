package com.hanabi.model.facade.clue;

import com.hanabi.model.facade.card.CardColor;

public class ColorClue implements Clue {
  private final CardColor color;

  public ColorClue(CardColor color) {
    this.color = color;
  }

  public CardColor getColor() {
    return color;
  }
}

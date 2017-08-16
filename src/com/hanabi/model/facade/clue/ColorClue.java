package com.hanabi.model.facade.clue;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.Color;

public class ColorClue implements Clue {
  private final Color color;

  public ColorClue(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }
}

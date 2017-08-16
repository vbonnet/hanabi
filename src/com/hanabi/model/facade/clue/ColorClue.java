package com.hanabi.model.facade.clue;

import com.hanabi.model.facade.card.Color;

class ColorClue implements Clue {
  private final Color color;

  public ColorClue(Color color) {
    this.color = color;
  }
}

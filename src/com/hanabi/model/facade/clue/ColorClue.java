package com.hanabi.model.facade.clue;

import com.hanabi.model.facade.card.Color;

public class ColorClue implements Clue {
  public final Color color;

  public ColorClue(Color color) {
    this.color = color;
  }
}

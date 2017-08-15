package com.hanabi.model.facade.card;

import com.hanabi.model.facade.Color;

public interface RevealedCard extends Card {
  Color getColor();
  int getValue();
}

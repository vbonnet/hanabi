package com.hanabi.model.facade.player;

import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.clue.ClueType;

import java.util.Collection;

public class PlayerClue {
  public final ClueType type;
  public final Collection<CardPlaceholder> cards;
  public final Object value;

  public PlayerClue(ClueType type, Collection<CardPlaceholder> cards, Object value) {
    this.type = type;
    this.cards = cards;
    this.value = value;
  }

}

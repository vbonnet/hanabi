package com.hanabi.model.facade.player;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.clue.ClueType;

import java.util.Collection;

public class PlayerClue {
  public final ClueType type;
  public final Collection<Card> cards;
  public final Object value;

  public PlayerClue(ClueType type, Collection<Card> cards, Object value) {
    this.type = type;
    this.cards = cards;
    this.value = value;
  }

}

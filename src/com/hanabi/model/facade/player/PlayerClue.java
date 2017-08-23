package com.hanabi.model.facade.player;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.clue.Clue;
import com.hanabi.model.facade.clue.ClueType;
import com.hanabi.model.facade.clue.ColorClue;
import com.hanabi.model.facade.clue.NumberClue;

import java.util.ArrayList;
import java.util.List;

public class PlayerClue {
  public ClueType type;
  public Object value;
  public final List<CardPlaceholder> cards = new ArrayList<>();

  public PlayerClue(Clue clue) {
    if (clue instanceof ColorClue) {
      ColorClue colorClue = (ColorClue) clue;
      this.type = ClueType.COLOR;
      this.value = colorClue.getColor();
    } else if (clue instanceof NumberClue) {
      NumberClue numberClue = (NumberClue)clue;
      this.type = ClueType.NUMBER;
      this.value = numberClue.getNumber();
    }
  }

  public boolean cardMatches(Card card) {
    switch (this.type) {
      case NUMBER:
        return card.getNumber() == value;
      case COLOR:
        return card.getColor() == value;
      default:
        return false;
    }
  }

  public void addPlaceholder(CardPlaceholder placeholder) {
    cards.add(placeholder);
  }
}

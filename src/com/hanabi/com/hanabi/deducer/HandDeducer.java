package com.hanabi.com.hanabi.deducer;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.Color;
import com.hanabi.model.facade.card.RevealedCard;
import com.hanabi.model.facade.player.Player;
import com.hanabi.model.facade.player.PlayerClue;
import com.hanabi.model.facade.player.PlayerGameView;

import java.util.HashMap;
import java.util.Map;

public class HandDeducer {
  final PlayerGameView view;
  final CardCounter cardCounter;
  final Map<Card, CardInference> hand = new HashMap<>();

  HandDeducer(PlayerGameView view) {
    this.view = view;
    cardCounter = new CardCounter(view.getAllCards());
    for (Card card : view.getHand()) {
      processCardDrawn(card);
    }
    try {
      for (Player player : view.getOtherPlayers()) {
        for (RevealedCard card : view.getPlayerHand(player)) {
          processCardRevealed(card);
        }
      }
    } catch (Exception e) {
      // whatever
    }
  }

  public void processClue(PlayerClue clue) {
    for (Map.Entry<Card, CardInference> entry : hand.entrySet()) {
      CardInference inference = entry.getValue();
      if (clue.cards.contains(entry.getKey())) {
        inference.setValue(clue.value);
      } else {
        inference.removeValue(clue.value);
      }
    }
  }

  public void processCardRevealed(RevealedCard card) {
    if (hand.containsKey(card)) {
      hand.remove(card);
    }
    cardCounter.remove(card);
  }

  public void processCardDrawn(Card card) {
    hand.put(card, new CardInference(cardCounter));
  }

  public boolean isGuaranteedPlayable(CardInference inference) {
    Map<Color, Integer> stacks = view.getPlayStacks();
    for (Color color : inference.getPossibleColors()) {
      Integer currentStackValue = stacks.get(color);
      for (Integer number : inference.getPossibleNumbers()) {
        if (number != currentStackValue + 1) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean isAlreadyPlayed(CardInference inference) {
    Map<Color, Integer> stacks = view.getPlayStacks();
    for (Color color : inference.getPossibleColors()) {
      Integer currentStackValue = stacks.get(color);
      for (Integer number : inference.getPossibleNumbers()) {
        if (number > currentStackValue) {
          return false;
        }
      }
    }
    return true;
  }
}

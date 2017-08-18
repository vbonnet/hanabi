package com.hanabi.com.hanabi.deducer;

import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.card.CardColor;
import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.player.Player;
import com.hanabi.model.facade.player.PlayerClue;
import com.hanabi.model.facade.player.PlayerGameView;

import java.util.HashMap;
import java.util.Map;

public class HandInference {
  final PlayerGameView view;
  final CardCounter cardCounter;
  final Map<CardPlaceholder, CardInference> hand = new HashMap<>();

  HandInference(PlayerGameView view) {
    this.view = view;
    cardCounter = new CardCounter(view.getAllCards());
    for (CardPlaceholder card : view.getHand()) {
      processCardDrawn(card);
    }
    try {
      for (Player player : view.getOtherPlayers()) {
        for (Card card : view.getPlayerHand(player)) {
          processCardRevealed(card);
        }
      }
    } catch (Exception e) {
      // whatever
    }
  }

  public void processClue(PlayerClue clue) {
    for (Map.Entry<CardPlaceholder, CardInference> entry : hand.entrySet()) {
      CardInference inference = entry.getValue();
      if (clue.cards.contains(entry.getKey())) {
        inference.setValue(clue.value);
      } else {
        inference.removeValue(clue.value);
      }
    }
  }

  public void processCardRevealed(Card card) {
    if (hand.containsKey(card)) {
      hand.remove(card);
    }
    cardCounter.remove(card);
  }

  public void processCardDrawn(CardPlaceholder card) {
    hand.put(card, new CardInference(cardCounter));
  }

  public boolean isGuaranteedPlayable(CardInference inference) {
    Map<CardColor, Integer> stacks = view.getPlayStacks();
    for (Card card : inference.getPossibleCards()) {
      if (card.getNumber() != stacks.get(card.getColor()) + 1) {
        return false;
      }
    }
    return true;
  }

  public boolean isAlreadyPlayed(CardInference inference) {
    Map<CardColor, Integer> stacks = view.getPlayStacks();
    for (Card card : inference.getPossibleCards()) {
      if (card.getNumber() > stacks.get(card.getColor())) {
        return false;
      }
    }
    return true;
  }
}

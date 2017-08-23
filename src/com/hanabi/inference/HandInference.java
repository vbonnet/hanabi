package com.hanabi.inference;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.CardColor;
import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.player.Player;
import com.hanabi.model.facade.player.PlayerClue;
import com.hanabi.model.facade.player.PlayerGameView;

import java.util.HashMap;
import java.util.Map;

public class HandInference {
  final PlayerGameView view;
  final CardCounter cardCounter;
  public final Map<CardPlaceholder, CardInference> hand = new HashMap<>();

  public HandInference(PlayerGameView view) {
    this(view, new CardCounter(view.getAllCards()));
  }

  public HandInference(PlayerGameView view, CardCounter counter) {
    this.view = view;
    this.cardCounter = counter;

    // Remove all cards in other player's hands.
    try {
      for (Player player : view.getOtherPlayers()) {
        for (Card card : view.getPlayerHand(player)) {
          cardCounter.remove(card);
        }
      }
    } catch (Exception e) {
      // whatever
    }

    // Add inference for each card in our hand.
    for (CardPlaceholder card : view.getHand()) {
      addCardInference(card);
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

  public void handleCardRevealed(Card card) {
    cardCounter.remove(card, this);
  }

  public void addCardInference(CardPlaceholder placeholder) {
    hand.put(placeholder, new CardInference(cardCounter));
  }

  public void removeCardInference(CardPlaceholder placeholder) {
    hand.remove(placeholder);
  }

  public boolean isGuaranteedPlayable(CardInference inference) {
    for (Card card : inference.getPossibleCards()) {
      if (card.getNumber() != valueOfStack(card.getColor()) + 1) {
        return false;
      }
    }
    return true;
  }

  public boolean isAlreadyPlayed(CardInference inference) {
    for (Card card : inference.getPossibleCards()) {
      if (card.getNumber() > valueOfStack(card.getColor())) {
        return false;
      }
    }
    return true;
  }

  private int valueOfStack(CardColor color) {
    Card card = view.getPlayStacks().get(color);
    return card == null ? 0 : card.getNumber();
  }
}

package com.hanabi.inference;

import com.hanabi.model.facade.card.Card;

public interface CardCounterListener {
  void handleCardRemoved(Card card);
}

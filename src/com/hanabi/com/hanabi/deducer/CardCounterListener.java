package com.hanabi.com.hanabi.deducer;

import com.hanabi.model.facade.card.Card;

public interface CardCounterListener {
  void handleCardRemoved(Card card);
}

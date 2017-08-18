package com.hanabi.com.hanabi.deducer;

import com.hanabi.model.facade.card.RevealedCard;

public interface CardCounterListener {
  void handleCardRemoved(RevealedCard card);
}

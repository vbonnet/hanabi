package com.hanabi.model.facade.player;

import com.hanabi.model.impl.CardImpl;

import java.util.List;

public interface PlayerHand {
  List<CardImpl> getCards();
}

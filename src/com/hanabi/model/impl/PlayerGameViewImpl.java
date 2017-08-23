package com.hanabi.model.impl;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.CardColor;
import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.player.Player;
import com.hanabi.model.facade.player.PlayerGameView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class PlayerGameViewImpl implements PlayerGameView {

  private final Player player;
  private final GameState state;

  PlayerGameViewImpl(GameState state, Player player) {
    this.player = player;
    this.state = state;
  }

  @Override
  public int getSizeOfDeck() {
    return state.getNumberCardsInDeck();
  }

  @Override
  public int getNumberOfLives() {
    return state.getNumberOfLives();
  }

  @Override
  public int getNumberOfClues() {
    return state.getNumberOfLives();
  }

  @Override
  public List<Card> getDiscard() {
    return Collections.unmodifiableList(state.discard.getCards());
  }

  @Override
  public List<Card> getPlayerHand(Player player) throws Exception {
    if (player == this.player) {
      throw new Exception("No peeking!");
    }
    return Collections.unmodifiableList(state.getPlayerHand(player).getCards());
  }

  @Override
  public List<CardPlaceholder> getHand() {
    return new ArrayList<>(state.getPlayerHand(player).getPlaceholders());
  }

  @Override
  public List<Card> getAllCards() {
    return state.allCards;
  }

  @Override
  public List<Player> getOtherPlayers() {
    ArrayList<Player> players = new ArrayList<>(state.getPlayers());
    players.remove(player);
    return players;
  }

  @Override
  public Map<CardColor, Card> getPlayStacks() {
    return Collections.unmodifiableMap(state.getCardStacks());
  }
}

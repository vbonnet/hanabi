package com.hanabi.model.impl;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.RevealedCard;
import com.hanabi.model.facade.player.Player;
import com.hanabi.model.facade.player.PlayerGameView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PlayerGameViewImpl implements PlayerGameView {

  private final Player player;
  private final GameState state;

  PlayerGameViewImpl(GameState state, Player player) {
    this.player = player;
    this.state = state;
  }

  @Override
  public int getSizeOfDeck() {
    return state.getDeck().getNumberCards();
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
  public Collection<RevealedCard> getDiscard() {
    return new ArrayList<>(state.discard.getCards());
  }

  @Override
  public Collection<RevealedCard> getPlayerHand(Player player) throws Exception {
    if (player == this.player) {
      throw new Exception("No peeking!");
    }
    return new ArrayList<>(state.getPlayerHand(player).getCards());
  }

  @Override
  public Collection<Card> getHand() {
    return new ArrayList<>(state.getPlayerHand(player).getCards());
  }

  @Override
  public Collection<RevealedCard> getAllCards() {
    return new ArrayList<>(state.allCards);
  }

  @Override
  public Collection<Player> getOtherPlayers() {
    ArrayList<Player> players = new ArrayList(state.getPlayers());
    players.remove(player);
    return players;
  }
}

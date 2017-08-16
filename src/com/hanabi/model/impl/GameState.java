package com.hanabi.model.impl;

import com.hanabi.model.facade.player.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState {
  protected final Map<Player, Hand> hands = new HashMap<>();
  protected final DiscardPile discard = new DiscardPile();
  protected final Board board = new Board();
  protected final Deck deck;

  protected int lives = 3;
  protected int clues = 8;

  public GameState(List<Player> playerList) throws Exception {
    this(playerList, null);
  }

  public GameState(List<Player> playerList, List<CardImpl> cardList) throws Exception {
    if (cardList == null) {
      this.deck = new Deck();
    } else {
      this.deck = new Deck(cardList);
    }

    int numberOfPlayers = playerList.size();
    int cardsPerPlayer;
    if (numberOfPlayers >= 4) {
      cardsPerPlayer = 4;
    } else {
      cardsPerPlayer = 5;
    }

    // Deal hands
    for (Player player : playerList) {
      hands.put(player, makeHand(cardsPerPlayer));
    }
  }

  public boolean isGameOver() {
    return (lives == 0 || deck.getNumberCards() == 0);
  }

  public int getScore() {
    if (lives == 0) {
      return 0;
    } else {
      return board.getScore();
    }
  }

  public Hand getPlayerHand(Player player) {
    return hands.get(player);
  }

  public Collection<Hand> getPlayerHands() {
    return hands.values();
  }

  public int getNumberOfLives() {
    return lives;
  }

  public int getNumberOfClues() {
    return clues;
  }

  public Deck getDeck() { return deck; }

  protected CardImpl drawCard(Player player) {
    CardImpl newCard = deck.getNextCard();
    if (newCard != null) {
      Hand playerHand = hands.get(player);
      playerHand.addCard(newCard);
    }
    return newCard;
  }

  protected void discardCard(Player player, CardImpl card) {
    Hand playerHand =  hands.get(player);
    playerHand.removeCard(card);
  }

  private Hand makeHand(int numberOfCards) {
    Hand hand = new Hand();
    for (int i = 0; i < numberOfCards; i++) {
      hand.addCard(deck.getNextCard());
    }
    return hand;
  }
}

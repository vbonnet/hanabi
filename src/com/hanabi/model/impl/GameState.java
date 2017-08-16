package com.hanabi.model.impl;

import com.hanabi.model.facade.player.Player;

import java.util.*;

public class GameState {
  protected final Map<Player, Hand> hands = new HashMap<>();
  protected final DiscardPile discard = new DiscardPile();
  protected final Collection<CardImpl> allCards;
  protected final Board board = new Board();
  protected final Deck deck;

  protected int lives = 3;
  protected int clues = 8;

  protected GameState(List<Player> playerList) throws Exception {
    this(playerList, null);
  }

  protected GameState(List<Player> playerList, List<CardImpl> cardList) throws Exception {
    if (cardList == null) {
      cardList = Deck.fullCardList();
      Collections.shuffle(cardList);
    }
    deck = new Deck(cardList);
    allCards = cardList;

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

  protected Hand getPlayerHand(Player player) {
    return hands.get(player);
  }

  protected Collection<Hand> getPlayerHands() {
    return hands.values();
  }

  public int getNumberOfLives() {
    return lives;
  }

  public int getNumberOfClues() {
    return clues;
  }

  public Deck getDeck() {
    return deck;
  }

  protected CardImpl drawCard(Player player) {
    CardImpl newCard = deck.getNextCard();
    if (newCard != null) {
      Hand playerHand = hands.get(player);
      playerHand.addCard(newCard);
    }
    return newCard;
  }

  protected void discardCard(Player player, CardImpl card) {
    Hand playerHand = hands.get(player);
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
package com.hanabi.model.impl;

import com.hanabi.model.facade.card.CardColor;
import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.player.Player;

import java.util.*;

public class GameState {
  private final Map<Player, Hand> hands = new HashMap<>();
  final DiscardPile discard = new DiscardPile();
  final Collection<CardImpl> allCards;
  final Board board = new Board();
  private final Deck deck;

  int lives = 3;
  int clues = 8;

  GameState(List<Player> playerList, List<CardImpl> cardList) {
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

  public Collection<Player> getPlayers() {
    return hands.keySet();
  }

  public Hand getPlayerHand(Player player) {
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

  public int getNumberCardsInDeck() {
    return deck.getNumberCards();
  }

  public HashMap<CardColor, Integer> getCardStacks() {
    return new HashMap<>(board.stacks);
  }

  private Deck getDeck() {
    return deck;
  }

  CardPlaceholder drawCard(Player player) {
    CardImpl newCard = deck.getNextCard();
    CardPlaceholder placeholder = null;
    if (newCard != null) {
      Hand playerHand = hands.get(player);
      placeholder = playerHand.addCard(newCard);
    }
    return placeholder;
  }

  void discardCard(Player player, CardPlaceholder placeholder) {
    Hand playerHand = hands.get(player);
    playerHand.removeCard(placeholder);
  }

  private Hand makeHand(int numberOfCards) {
    Hand hand = new Hand();
    for (int i = 0; i < numberOfCards; i++) {
      hand.addCard(deck.getNextCard());
    }
    return hand;
  }
}

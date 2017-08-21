package com.hanabi.util;

import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.card.CardColor;
import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.player.Player;
import com.hanabi.model.facade.player.PlayerGameView;
import com.hanabi.model.impl.Deck;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FakeGameView implements PlayerGameView {
  public Map<CardPlaceholder, Card> hand;
  public List<Card> deck;
  public List<Card> discard;

  public LinkedHashMap<Player, List<Card>> otherPlayers = new LinkedHashMap<>();
  public Map<CardColor, Card> playStacks;

  public FakeGameView() {
    this(new HashMap<>(), new ArrayList<>(Deck.fullCardList()), new ArrayList<>());
  }

  public FakeGameView(
      Map<CardPlaceholder, Card> hand,
      List<Card> deck,
      List<Card> discard) {
    this(hand, deck, discard, new HashMap<>());
    for (CardColor color : CardColor.values()) {
      playStacks.put(color, null);
    }
  }

  public FakeGameView(
        Map<CardPlaceholder, Card> hand,
        List<Card> deck,
        List<Card> discard,
        Map<CardColor, Card> playStacks) {
    this.hand = hand;
    this.deck = deck;
    this.discard = discard;
    this.playStacks = playStacks;
  }

  @Override
  public Map<CardColor, Card> getPlayStacks() {
    return playStacks;
  }

  @Override
  public List<Player> getOtherPlayers() {
    return new ArrayList<>(otherPlayers.keySet());
  }

  @Override
  public int getSizeOfDeck() {
    return deck.size();
  }

  @Override
  public List<Card> getDiscard() {
    return discard;
  }

  @Override
  public List<Card> getPlayerHand(Player player) throws Exception {
    return otherPlayers.get(player);
  }

  @Override
  public List<CardPlaceholder> getHand() {
    return new ArrayList<>(hand.keySet());
  }

  @Override
  public List<Card> getAllCards() {
    return Stream.of(
        deck.stream(),
        discard.stream(),
        hand.values().stream()).flatMap(s -> s).collect(Collectors.toList());
  }

  @Override
  public int getNumberOfLives() {
    return 0;
  }

  @Override
  public int getNumberOfClues() {
    return 0;
  }
}

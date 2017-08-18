package com.hanabi.util;

import com.hanabi.model.facade.card.CardPlaceholder;
import com.hanabi.model.facade.card.CardColor;
import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.player.Player;
import com.hanabi.model.facade.player.PlayerGameView;
import com.hanabi.model.impl.Deck;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FakeGameView implements PlayerGameView {
  public Map<CardPlaceholder, Card> hand;
  public List<Card> deck;
  public List<Card> discard;

  public Map<Player, List<Card>> otherPlayers = new HashMap<>();
  public Map<CardColor, Integer> playStacks;

  public FakeGameView() {
    this(new HashMap<>(), new ArrayList<>(Deck.fullCardList()), new ArrayList<>());
  }

  public FakeGameView(
      Map<CardPlaceholder, Card> hand,
      List<Card> deck,
      List<Card> discard) {
    this(hand, deck, discard, new HashMap<>());
    for (CardColor color : CardColor.values()) {
      playStacks.put(color, 0);
    }
  }

  public FakeGameView(
        Map<CardPlaceholder, Card> hand,
        List<Card> deck,
        List<Card> discard,
        Map<CardColor, Integer> playStacks) {
    this.hand = hand;
    this.deck = deck;
    this.discard = discard;
    this.playStacks = playStacks;
  }

  @Override
  public Map<CardColor, Integer> getPlayStacks() {
    return playStacks;
  }

  @Override
  public Collection<Player> getOtherPlayers() {
    return otherPlayers.keySet();
  }

  @Override
  public int getSizeOfDeck() {
    return deck.size();
  }

  @Override
  public Collection<Card> getDiscard() {
    return discard;
  }

  @Override
  public Collection<Card> getPlayerHand(Player player) throws Exception {
    return otherPlayers.get(player);
  }

  @Override
  public List<CardPlaceholder> getHand() {
    return new ArrayList<>(hand.keySet());
  }

  @Override
  public Collection<Card> getAllCards() {
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

package com.hanabi.util;

import com.hanabi.model.facade.card.Card;
import com.hanabi.model.facade.card.Color;
import com.hanabi.model.facade.card.RevealedCard;
import com.hanabi.model.facade.player.Player;
import com.hanabi.model.facade.player.PlayerGameView;
import com.hanabi.model.impl.Deck;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FakeGameView implements PlayerGameView {
  public List<RevealedCard> hand;
  public List<RevealedCard> deck;
  public List<RevealedCard> discard;

  public Map<Player, List<RevealedCard>> otherPlayers = new HashMap<>();
  public Map<Color, Integer> playStacks;

  public FakeGameView() {
    this(new ArrayList<>(), new ArrayList<>(Deck.fullCardList()), new ArrayList<>());
  }

  public FakeGameView(
      List<RevealedCard> hand,
      List<RevealedCard> deck,
      List<RevealedCard> discard) {
    this(hand, deck, discard, new HashMap<>());
    for (Color color : Color.values()) {
      playStacks.put(color, 0);
    }
  }

  public FakeGameView(
        List<RevealedCard> hand,
        List<RevealedCard> deck,
        List<RevealedCard> discard,
        Map<Color, Integer> playStacks) {
    this.hand = hand;
    this.deck = deck;
    this.discard = discard;
    this.playStacks = playStacks;
  }

  @Override
  public Map<Color, Integer> getPlayStacks() {
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
  public Collection<RevealedCard> getDiscard() {
    return discard;
  }

  @Override
  public Collection<RevealedCard> getPlayerHand(Player player) throws Exception {
    return otherPlayers.get(player);
  }

  @Override
  public Collection<Card> getHand() {
    return new ArrayList<>(hand);
  }

  @Override
  public Collection<RevealedCard> getAllCards() {
    return Stream.of(
        deck.stream(),
        discard.stream(),
        hand.stream()).flatMap(s -> s).collect(Collectors.toList());
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

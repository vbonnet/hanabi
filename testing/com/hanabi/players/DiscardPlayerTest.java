package com.hanabi.players;

import com.hanabi.model.facade.player.Player;
import com.hanabi.model.impl.GameEngine;
import com.hanabi.model.impl.GameState;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class DiscardPlayerTest {

  @Test
  public void testDiscardPlayer() throws Exception {
    ArrayList<Player> players = new ArrayList<>();
    players.add(new DiscardPlayer());
    players.add(new DiscardPlayer());
    GameEngine engine = new GameEngine(players);
    GameState state = engine.getState();
    engine.initialize();
    engine.run();

    Assert.assertTrue(
        state.isGameOver());
    Assert.assertEquals(
        state.getNumberOfLives(),
        3);
    Assert.assertEquals(
        state.getNumberCardsInDeck(),
        0);
  }
}
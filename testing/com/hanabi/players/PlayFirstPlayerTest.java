package com.hanabi.players;

import com.hanabi.model.facade.player.Player;
import com.hanabi.model.impl.GameEngine;
import com.hanabi.model.impl.GameState;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PlayFirstPlayerTest {

  @Test
  public void testDiscardPlayer() throws Exception {
    ArrayList<Player> players = new ArrayList<>();
    players.add(new PlayFirstPlayer());
    players.add(new PlayFirstPlayer());
    GameEngine engine = new GameEngine(players);
    GameState state = engine.getState();
    engine.run();

    Assert.assertTrue(state.isGameOver());
    // Technically this can, once in a blue moon, not be true. That would require the perfect game to have been
    // played simply by playing the first card of every player. I'm okay with those odds.
    Assert.assertEquals(
        state.getNumberOfLives(),
        0);
  }
}
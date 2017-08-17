package com.hanabi.com.hanabi.deducer;

import com.hanabi.model.facade.card.Color;
import com.hanabi.util.FakeGameView;
import org.junit.Assert;
import org.junit.Test;


public class HandInferenceTest {

  @Test
  public void testPlayable() {
    FakeGameView view = new FakeGameView();
    HandInference handInference = new HandInference(view);
    CardInference cardInference;

    // One's start out playable
    cardInference = new CardInference(handInference.cardCounter);
    cardInference.setValue(1);
    Assert.assertTrue(
        handInference.isGuaranteedPlayable(cardInference));

    // Two's are not playable
    cardInference = new CardInference(handInference.cardCounter);
    cardInference.setValue(1);
    Assert.assertTrue(
        handInference.isGuaranteedPlayable(cardInference));

    // Play the red one
    view.playStacks.put(Color.RED, 1);

    // Now not all one's are playable
    cardInference = new CardInference(handInference.cardCounter);
    cardInference.setValue(1);
    Assert.assertFalse(
        handInference.isGuaranteedPlayable(cardInference));

    // All but the red one should be playable
    cardInference = new CardInference(handInference.cardCounter);
    cardInference.setValue(1);
    cardInference.removeColor(Color.RED);
    Assert.assertTrue(
        handInference.isGuaranteedPlayable(cardInference));

    // All twos should not be playable
    cardInference = new CardInference(handInference.cardCounter);
    cardInference.setValue(2);
    Assert.assertFalse(
        handInference.isGuaranteedPlayable(cardInference));


    // Red two should be playable
    cardInference = new CardInference(handInference.cardCounter);
    cardInference.setValue(2);
    cardInference.setColor(Color.RED);
    Assert.assertTrue(
        handInference.isGuaranteedPlayable(cardInference));
  }

  @Test
  public void testIsPlayed() {
    FakeGameView view = new FakeGameView();
    HandInference handInference = new HandInference(view);
    CardInference inference;

    // One's are not already played.
    inference = new CardInference(handInference.cardCounter);
    inference.setValue(1);
    Assert.assertFalse(
        handInference.isAlreadyPlayed(inference));

    for (Color color : Color.values()) {
      view.playStacks.put(color, 2);
    }

    // One's are played
    inference = new CardInference(handInference.cardCounter);
    inference.setValue(1);
    Assert.assertTrue(
        handInference.isAlreadyPlayed(inference));

    // One's and two's are played
    inference = new CardInference(handInference.cardCounter);
    inference.removeNumber(3);
    inference.removeNumber(4);
    inference.removeNumber(5);
    Assert.assertTrue(
        handInference.isAlreadyPlayed(inference));

    // Three's are not played
    inference = new CardInference(handInference.cardCounter);
    inference.setValue(3);
    Assert.assertFalse(
        handInference.isAlreadyPlayed(inference));

    // Play red three
    view.playStacks.put(Color.RED, 3);

    // Red three is played
    inference = new CardInference(handInference.cardCounter);
    inference.setValue(3);
    inference.setColor(Color.RED);
    Assert.assertTrue(
        handInference.isAlreadyPlayed(inference));

  }

}
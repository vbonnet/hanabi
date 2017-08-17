package com.hanabi.com.hanabi.deducer;

import com.hanabi.model.facade.card.Color;
import com.hanabi.util.FakeGameView;
import org.junit.Assert;
import org.junit.Test;


public class HandDeducerTest {

  @Test
  public void testPlayable() {
    FakeGameView view = new FakeGameView();
    HandDeducer deducer = new HandDeducer(view);
    CardInference inference;

    // One's start out playable
    inference = new CardInference();
    inference.setValue(1);
    Assert.assertTrue(
        deducer.isGuaranteedPlayable(inference));

    // Two's are not playable
    inference = new CardInference();
    inference.setValue(1);
    Assert.assertTrue(
        deducer.isGuaranteedPlayable(inference));

    // Play the red one
    view.playStacks.put(Color.RED, 1);

    // Now not all one's are playable
    inference = new CardInference();
    inference.setValue(1);
    Assert.assertFalse(
        deducer.isGuaranteedPlayable(inference));

    // All but the red one should be playable
    inference = new CardInference();
    inference.setValue(1);
    inference.removeColor(Color.RED);
    Assert.assertTrue(
        deducer.isGuaranteedPlayable(inference));

    // All twos should not be playable
    inference = new CardInference();
    inference.setValue(2);
    Assert.assertFalse(
        deducer.isGuaranteedPlayable(inference));


    // Red two should be playable
    inference = new CardInference();
    inference.setValue(2);
    inference.setColor(Color.RED);
    Assert.assertTrue(
        deducer.isGuaranteedPlayable(inference));
  }

  @Test
  public void testIsPlayed() {
    FakeGameView view = new FakeGameView();
    HandDeducer deducer = new HandDeducer(view);
    CardInference inference;

    // One's are not already played.
    inference = new CardInference();
    inference.setValue(1);
    Assert.assertFalse(
        deducer.isAlreadyPlayed(inference));

    for (Color color : Color.values()) {
      view.playStacks.put(color, 2);
    }

    // One's are played
    inference = new CardInference();
    inference.setValue(1);
    Assert.assertTrue(
        deducer.isAlreadyPlayed(inference));

    // One's and two's are played
    inference = new CardInference();
    inference.removeNumber(3);
    inference.removeNumber(4);
    inference.removeNumber(5);
    Assert.assertTrue(
        deducer.isAlreadyPlayed(inference));

    // Three's are not played
    inference = new CardInference();
    inference.setValue(3);
    Assert.assertFalse(
        deducer.isAlreadyPlayed(inference));

    // Play red three
    view.playStacks.put(Color.RED, 3);

    // Red three is played
    inference = new CardInference();
    inference.setValue(3);
    inference.setColor(Color.RED);
    Assert.assertTrue(
        deducer.isAlreadyPlayed(inference));

  }

}
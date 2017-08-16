package com.hanabi.model.facade.clue;

public class NumberClue implements Clue {
  private final int value;

  public NumberClue(int value) {
    this.value = value;
  }

  public Integer getNumber() {
    return value;
  }
}

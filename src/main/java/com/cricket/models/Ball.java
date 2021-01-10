package com.cricket.models;

import java.util.Objects;

import com.cricket.entitymodels.Player;

public class Ball {
  private final int run;
  private final Player striker;
  private final Player bowler;
  private boolean isValid;
  private ExtraBowlTypes extraBowls;

  public Ball(int run, Player striker, Player bowler) {
    this.run = run;
    this.striker = striker;
    this.bowler = bowler;
  }

  public int getRun() {
    return run;
  }

  public Player getStriker() {
    return striker;
  }

  public Player getBowler() {
    return bowler;
  }

  public boolean isValid() {
    return isValid;
  }

  public void setValid(boolean valid) {
    isValid = valid;
  }

  public ExtraBowlTypes getExtraBowls() {
    return extraBowls;
  }

  public void setExtraBowls(ExtraBowlTypes extraBowls) {
    this.extraBowls = extraBowls;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ball ball = (Ball)o;
    return run == ball.run && isValid == ball.isValid && Objects.equals(striker, ball.striker) && Objects.equals(bowler, ball.bowler) && extraBowls == ball.extraBowls;
  }

  @Override
  public int hashCode() {
    return Objects.hash(run, striker, bowler, isValid, extraBowls);
  }
}

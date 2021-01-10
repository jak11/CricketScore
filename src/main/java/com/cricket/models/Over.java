package com.cricket.models;

import java.util.ArrayList;
import java.util.List;

public class Over {
  private final List<Ball> balls;

  public Over(List<Ball> balls) {
    this.balls = balls;
  }

  public List<Ball> getBalls() {
    return new ArrayList<>(this.balls);
  }
}

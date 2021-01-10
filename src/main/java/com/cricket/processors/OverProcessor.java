package com.cricket.processors;

import java.util.List;
import java.util.Objects;

import com.cricket.models.Ball;
import com.cricket.models.Over;

public class OverProcessor {
  public void processOver(Over over){
    if (Objects.isNull(over)) return;
    List<Ball> balls = over.getBalls();
    balls.forEach(ball -> {

    });
  }
}

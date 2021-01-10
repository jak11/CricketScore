package com.cricket.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BattingScore extends PlayerScore{
  private Map<Integer, Integer> scoreMap;
  int total;
  int balls;
  public BattingScore(String playerId, int matchId, ScoreType scoreType) {
    super(playerId, matchId, scoreType);
    scoreMap = new HashMap<>();
  }

  public void updateScore(int run, int ball){
    if(scoreMap.containsKey(run)){
      scoreMap.put(run, scoreMap.get(run) + 1);
    } else {
      scoreMap.put(run, 1);
    }
    total += run;
    balls += ball;
  }

  public int getTotal() {
    return total;
  }

  public int getBalls() {
    return balls;
  }

  public Map<Integer, Integer> getScoreMap() {
    return Collections.unmodifiableMap(scoreMap);
  }
}

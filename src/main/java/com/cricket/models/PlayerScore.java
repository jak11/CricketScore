package com.cricket.models;

import java.util.Objects;

public abstract class PlayerScore {
  private String playerId;
  private int matchId;
  ScoreType scoreType;

  public PlayerScore(String playerId, int matchId, ScoreType scoreType) {
    this.playerId = playerId;
    this.matchId = matchId;
    this.scoreType = scoreType;
  }

  public String getPlayerId() {
    return playerId;
  }

  public int getMatchId() {
    return matchId;
  }

  public ScoreType getScoreType() {
    return scoreType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlayerScore that = (PlayerScore)o;
    return matchId == that.matchId && Objects.equals(playerId, that.playerId) && scoreType == that.scoreType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerId, matchId, scoreType);
  }
}

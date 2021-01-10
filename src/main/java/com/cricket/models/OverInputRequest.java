package com.cricket.models;

import java.util.List;

public class OverInputRequest {
  private Long matchId;
  private Long teamId;
  private List<String> balls;

  public Long getMatchId() {
    return matchId;
  }

  public void setMatchId(Long matchId) {
    this.matchId = matchId;
  }

  public Long getTeamId() {
    return teamId;
  }

  public void setTeamId(Long teamId) {
    this.teamId = teamId;
  }

  public List<String> getBalls() {
    return balls;
  }

  public void setBalls(List<String> balls) {
    this.balls = balls;
  }
}

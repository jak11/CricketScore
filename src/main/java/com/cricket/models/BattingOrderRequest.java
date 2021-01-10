package com.cricket.models;

import java.util.List;

public class BattingOrderRequest {
  private Long matchId;
  private Long teamId;
  private List<String> battingOrder; //storing players prder

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

  public List<String> getBattingOrder() {
    return battingOrder;
  }

  public void setBattingOrder(List<String> battingOrder) {
    this.battingOrder = battingOrder;
  }
}

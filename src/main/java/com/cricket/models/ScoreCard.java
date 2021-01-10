package com.cricket.models;

import java.util.List;

public class ScoreCard {
  private List<TeamScoreSummary> teamSummary;
  private String result;

  public List<TeamScoreSummary> getTeamSummary() {
    return teamSummary;
  }

  public void setTeamSummary(List<TeamScoreSummary> teamSummary) {
    this.teamSummary = teamSummary;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}

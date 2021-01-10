package com.cricket.models;

import java.util.List;

import com.cricket.entitymodels.PlayerBattingScore;
import com.cricket.entitymodels.Team;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("team.players")
public class TeamScoreSummary {
  List<PlayerBattingScore> playerBattingScores;
  Team team;
  int totalRun;
  int wickets;
  double over;

  public List<PlayerBattingScore> getPlayerBattingScores() {
    return playerBattingScores;
  }

  public void setPlayerBattingScores(List<PlayerBattingScore> playerBattingScores) {
    this.playerBattingScores = playerBattingScores;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public int getTotalRun() {
    return totalRun;
  }

  public void setTotalRun(int totalRun) {
    this.totalRun = totalRun;
  }

  public int getWickets() {
    return wickets;
  }

  public void setWickets(int wickets) {
    this.wickets = wickets;
  }

  public double getOver() {
    return over;
  }

  public void setOver(double over) {
    this.over = over;
  }
}

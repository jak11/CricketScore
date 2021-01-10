package com.cricket.models;

import java.util.Set;

public class MatchInputRequest {
  private int noOfPlayersPerTeam;
  private int noOfOvers;
  private Set<Long> teams;

  public int getNoOfPlayersPerTeam() {
    return noOfPlayersPerTeam;
  }

  public void setNoOfPlayersPerTeam(int noOfPlayersPerTeam) {
    this.noOfPlayersPerTeam = noOfPlayersPerTeam;
  }

  public int getNoOfOvers() {
    return noOfOvers;
  }

  public void setNoOfOvers(int noOfOvers) {
    this.noOfOvers = noOfOvers;
  }

  public Set<Long> getTeams() {
    return teams;
  }

  public void setTeams(Set<Long> teams) {
    this.teams = teams;
  }
}

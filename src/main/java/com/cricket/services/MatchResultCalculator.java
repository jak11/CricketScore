package com.cricket.services;

import com.cricket.entitymodels.Match;
import com.cricket.models.TeamScoreSummary;
import org.springframework.stereotype.Component;

@Component
public class MatchResultCalculator {
  public String findMatchResult(Match match, TeamScoreSummary team1, TeamScoreSummary team2){
    boolean firstInningOver = false, sceondInningOver = false;
    String result = "Results pending";
    if(team1 == null || team2 == null){
      return  result;
    }


    if(team1.getOver() == match.getOversPerTeam() || team1.getWickets() == match.getPlayersPerTeam() - 1){
      firstInningOver = true;
    }

    if(team2.getOver() == match.getOversPerTeam() || team2.getWickets() == match.getPlayersPerTeam() - 1){
      sceondInningOver = true;
    }



    if(firstInningOver && sceondInningOver){
      int runDiff = team1.getTotalRun() - team2.getTotalRun();
      if(runDiff > 0){
        result = "Team " + team1.getTeam().getId() + "won by "+ runDiff + " runs";
      } else if(runDiff == 0){
        result = "Match Tied";
      } else {
        result = "Team " + team2.getTeam().getId() + "won by "+ (-runDiff) + " runs";
      }
    }
    return result;
  }
}

package com.cricket.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cricket.entitymodels.Match;
import com.cricket.entitymodels.MatchBattingScore;
import com.cricket.entitymodels.PlayerBattingScore;
import com.cricket.models.ScoreCard;
import com.cricket.models.TeamScoreSummary;
import com.cricket.repository.MatchBattingScoreRepository;
import com.cricket.repository.MatchRepository;
import com.cricket.repository.PlayerBattingScoreRepository;
import com.cricket.services.MatchResultCalculator;
import com.cricket.services.ScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreCardServiceImpl implements ScoreCardService {
  @Autowired
  private PlayerBattingScoreRepository playerBattingScoreRepository;

  @Autowired
  private MatchRepository matchRepository;

  @Autowired
  private MatchResultCalculator matchResultCalculator;

  @Autowired
  private MatchBattingScoreRepository matchBattingScoreRepository;

  @Override
  public ScoreCard getScoreCard(Long matchId) {
    Match match = matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("MtachId invalid"));

    ScoreCard scoreCard = new ScoreCard();

    List<PlayerBattingScore>  playerBattingScores = playerBattingScoreRepository.findByMatchId(matchId);

    Map<Long, List<PlayerBattingScore>>  mapOfScores = new HashMap<>();

    playerBattingScores.forEach( playerBattingScore -> {
      if(mapOfScores.containsKey(playerBattingScore.getTeam().getId())){
        mapOfScores.get(playerBattingScore.getTeam().getId()).add(playerBattingScore);
      } else {
        List<PlayerBattingScore> temp = new ArrayList<>();
        temp.add(playerBattingScore);
        mapOfScores.put(playerBattingScore.getTeam().getId(), temp);
      }
    });

   List<TeamScoreSummary> teams = new ArrayList<>();

   mapOfScores.forEach( (teamId, playerBattingScores1) -> {
      MatchBattingScore matchBattingScore =  matchBattingScoreRepository.findByMatchIdTeamId(matchId, teamId);
      TeamScoreSummary teamScoreSummary = prepareTeamSummary(teamId, playerBattingScores1);
      if(matchBattingScore != null){
        teamScoreSummary.setTotalRun(matchBattingScore.getTotal());
      }
      teams.add(teamScoreSummary);
    });

   TeamScoreSummary team1 =null, team2 = null;

   if(teams.size() == 2){
     team1 = teams.get(0);
     team2 = teams.get(1);
   } else if(teams.size() == 1){
     team1 = teams.get(0);
   } else {
     throw new RuntimeException("Teams not onfigured correctly");
   }

   scoreCard.setResult(matchResultCalculator.findMatchResult(match, team1, team2));
   scoreCard.setTeamSummary(teams);
   return scoreCard;
  }

  private TeamScoreSummary prepareTeamSummary(Long teamId, List<PlayerBattingScore> playerBattingScores1) {
    TeamScoreSummary teamScoreSummary = new TeamScoreSummary();
    teamScoreSummary.setTeam(playerBattingScores1.get(0).getTeam());
    teamScoreSummary.setPlayerBattingScores(playerBattingScores1);
    int totalRuns = 0;
    int wickets = 0;
    int balls = 0;

    for (PlayerBattingScore playerBattingScore: playerBattingScores1) {
      totalRuns += playerBattingScore.getTotal();
      balls += playerBattingScore.getBalls();
      if(playerBattingScore.isOut()){
        wickets+=1;
      }
    }

    int over = balls/6;
    int remainBalls = balls%6;

    double finalover = remainBalls*0.1 + over;
    teamScoreSummary.setOver(finalover);
    teamScoreSummary.setTotalRun(totalRuns);
    teamScoreSummary.setWickets(wickets);
    return teamScoreSummary;
  }
}

package com.cricket.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cricket.entitymodels.BattingOrder;
import com.cricket.entitymodels.MatchBattingScore;
import com.cricket.entitymodels.PlayerBattingScore;
import com.cricket.models.OverInputRequest;
import com.cricket.repository.BattingOrderRepository;
import com.cricket.repository.MatchBattingScoreRepository;
import com.cricket.repository.PlayerBattingScoreRepository;
import com.cricket.services.OverProcessorService;
import com.cricket.services.StrikeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OverProcessorServiceImpl implements OverProcessorService {
  @Autowired
  private BattingOrderRepository battingOrderRepository;

  @Autowired
  private PlayerBattingScoreRepository playerBattingScoreRepository;

  @Autowired
  private StrikeCalculator strikeCalculator;

  @Autowired
  private MatchBattingScoreRepository matchBattingScoreRepository;

  @Override
  public void processOver(OverInputRequest overInputRequest) {
    //validators for overs
    Long matchId = overInputRequest.getMatchId();
    Long teamId = overInputRequest.getTeamId();
    List<String> balls = overInputRequest.getBalls();

    List<BattingOrder>  battingOrders = battingOrderRepository.findUserByStatusAndName(overInputRequest.getMatchId(), overInputRequest.getTeamId());
    List<PlayerBattingScore> currentPlayers = playerBattingScoreRepository.findOnStrikePlayers(matchId, teamId);

    if(currentPlayers.size() < 2){
      throw new RuntimeException("Can't process over no players left");
    }

    PlayerBattingScore striker = currentPlayers.get(0);
    PlayerBattingScore nonStriker = currentPlayers.get(1);
    int totalRuns = 0;
    int wickets = 0;

    for (String ball: balls ) {
      if(checkIfWicket(ball)){
        updateStriker(striker);
        //currentPlayers.remove(striker);
        //fetch next striker
        striker = fetchNextStriker(battingOrders, striker, currentPlayers);
        wickets++;
        if(striker == null){
          break;
        }
      } else {
        if(checkIfRunsOrExtra(ball)){
          //update run and change strike based on runs
          int run = Integer.parseInt(ball);
          updateStats(striker, ball);
          if(strikeCalculator.isChangeOfStrike(run)){
            PlayerBattingScore temp = striker;
            striker = nonStriker;
            nonStriker = temp;
          }
          totalRuns +=run;
        } else {
          totalRuns +=1;
        }
      }
    }

    List<PlayerBattingScore> storeStats = new ArrayList<>();
    if(striker != null){
      striker.setStrike(2);
      storeStats.add(striker);
    }

    if(nonStriker != null){
      nonStriker.setStrike(1);
      storeStats.add(nonStriker);
    }

    MatchBattingScore matchBattingScore = matchBattingScoreRepository.findByMatchIdTeamId(matchId, teamId);
    if(matchBattingScore == null){
      matchBattingScore = new MatchBattingScore();
      matchBattingScore.setOvers(matchBattingScore.getOvers());
      matchBattingScore.setTotal(totalRuns);
      matchBattingScore.setWickets(wickets);
      matchBattingScore.setMatch(striker.getMatch());
      matchBattingScore.setTeam(striker.getTeam());
    } else {
      matchBattingScore.setOvers(matchBattingScore.getOvers() + 1);
      matchBattingScore.setTotal(matchBattingScore.getTotal() + totalRuns);
      matchBattingScore.setWickets(matchBattingScore.getWickets() + wickets);
    }

    matchBattingScoreRepository.save(matchBattingScore);
    playerBattingScoreRepository.saveAll(storeStats);
  }

  private PlayerBattingScore fetchNextStriker(List<BattingOrder> battingOrders, PlayerBattingScore striker, List<PlayerBattingScore> currentPlayers) {
    int maxSequence = 0;
    for(PlayerBattingScore playerBattingScore: currentPlayers){
      Optional<BattingOrder> strikerBattingOrderOptional1 = battingOrders.stream().filter(battingOrder -> battingOrder.getPlayer().getId().equals(playerBattingScore.getPlayer().getId())).findFirst();
      BattingOrder strikerBattingOrder1 = strikerBattingOrderOptional1.orElseThrow(() -> new RuntimeException("Something wrong while proccessing the over"));
      maxSequence = Math.max(maxSequence, strikerBattingOrder1.getSequence());
    };

    Optional<BattingOrder> found = Optional.empty();
    for (BattingOrder battingOrder : battingOrders) {
      if (battingOrder.getSequence() == maxSequence + 1) {
        found = Optional.of(battingOrder);
        break;
      }
    }
    if(!found.isPresent()){
      return null;
    }

    BattingOrder newBattingOrder = found.get();

    currentPlayers.remove(striker);
    PlayerBattingScore playerBattingScore = new PlayerBattingScore();
    playerBattingScore.setStrike(1);
    playerBattingScore.setPlayer(newBattingOrder.getPlayer());
    playerBattingScore.setMatch(newBattingOrder.getMatch());
    playerBattingScore.setTeam(newBattingOrder.getTeam());
    playerBattingScore = playerBattingScoreRepository.save(playerBattingScore);
    currentPlayers.add(playerBattingScore);

    return playerBattingScore;
  }

  private void updateStats(PlayerBattingScore striker, String ball) {
    int run = Integer.parseInt(ball);
    if(run == 3){
      striker.setThrees(striker.getThrees() + 1);
    } else if(run == 4){
      striker.setFours(striker.getFours() + 1);
    }

    striker.setTotal(striker.getTotal() + run);
    striker.setBalls(striker.getBalls() + 1);
  }

  //considering only simple case here- no runout extra
  private void updateStriker(PlayerBattingScore striker) {
    striker.setOut(true);
    striker.setBalls(striker.getBalls() + 1);
    striker.setStrike(-1);
    playerBattingScoreRepository.save(striker);
  }

  private boolean checkIfRunsOrExtra(String ball){
    return ball.matches("-?(0|[1-9]\\d*)");
  }

  private boolean checkIfWicket(String ball){
    return ball.equals("W");
  }
}

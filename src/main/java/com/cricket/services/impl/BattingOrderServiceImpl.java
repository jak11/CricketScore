package com.cricket.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.cricket.entitymodels.BattingOrder;
import com.cricket.entitymodels.Match;
import com.cricket.entitymodels.Player;
import com.cricket.entitymodels.PlayerBattingScore;
import com.cricket.entitymodels.Team;
import com.cricket.models.BattingOrderRequest;
import com.cricket.repository.BattingOrderRepository;
import com.cricket.repository.MatchRepository;
import com.cricket.repository.PlayerBattingScoreRepository;
import com.cricket.repository.PlayerRepository;
import com.cricket.repository.TeamRepository;
import com.cricket.services.BattingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BattingOrderServiceImpl implements BattingOrderService {
  @Autowired
  private BattingOrderRepository battingOrderRepository;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private MatchRepository matchRepository;

  @Autowired
  private PlayerRepository playerRepository;

  @Autowired
  private PlayerBattingScoreRepository playerBattingScoreRepository;

  @Override
  public List<BattingOrder> saveBattingOrder(BattingOrderRequest battingOrderRequest) {
    Match match = matchRepository.findById(battingOrderRequest.getMatchId()).orElseThrow(
        () -> new IllegalArgumentException("Match Id is incorrect"));

    Team team = teamRepository.findById(battingOrderRequest.getTeamId()).orElseThrow(
        () -> new IllegalArgumentException("Team Id is incorrect"));

    List<String> playerNamesOrder = battingOrderRequest.getBattingOrder();
    List<Player> players = playerRepository.findByNameIn(playerNamesOrder);

    List<String> playerNames = players.stream().map(Player::getName).collect(Collectors.toList());
    if(!(playerNamesOrder.containsAll(playerNames) && playerNamesOrder.size() ==  playerNames.size())){
      throw new IllegalArgumentException("Playernames are not present");
    }

    List<BattingOrder> battingOrders = new ArrayList<>();
    for (int i = 0; i < players.size(); i++) {
      BattingOrder battingOrder = new BattingOrder();
      battingOrder.setMatch(match);
      battingOrder.setTeam(team);
      battingOrder.setSequence(i + 1);
      battingOrder.setPlayer(players.get(i));
      battingOrders.add(battingOrder);
    }

    //update the PlayerBattigScore
    PlayerBattingScore playerBattingScore1 = new PlayerBattingScore();
    playerBattingScore1.setPlayer(battingOrders.get(0).getPlayer());
    playerBattingScore1.setTeam(team);
    playerBattingScore1.setMatch(match);
    playerBattingScore1.setStrike(1);

    PlayerBattingScore playerBattingScore2 = new PlayerBattingScore();
    playerBattingScore2.setPlayer(battingOrders.get(1).getPlayer());
    playerBattingScore2.setTeam(team);
    playerBattingScore2.setMatch(match);
    playerBattingScore2.setStrike(2);

    playerBattingScoreRepository.saveAll(Arrays.asList(playerBattingScore1, playerBattingScore2));
    return battingOrderRepository.saveAll(battingOrders);
  }
}

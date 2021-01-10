package com.cricket.services.impl;

import java.util.List;
import java.util.Optional;

import com.cricket.entitymodels.Player;
import com.cricket.entitymodels.Team;
import com.cricket.repository.TeamRepository;
import com.cricket.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
  private TeamRepository teamRepository;

  @Autowired
  public TeamServiceImpl(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  @Override
  public Team createTeam(int id, List<Player> players) {
    //validate the players and id
    Team team = new Team();
    team.addPlayer(players);
    return team;
  }

  @Override
  public Optional<Team> getTeam(long id) {
    return teamRepository.findById(id);
  }
}

package com.cricket.services;

import java.util.List;
import java.util.Optional;

import com.cricket.entitymodels.Player;
import com.cricket.entitymodels.Team;

public interface TeamService {
  Team createTeam(int id, List<Player> players);
  Optional<Team> getTeam(long id);
}

package com.cricket.services;

import java.util.Optional;

import com.cricket.entitymodels.Player;

public interface PlayerService {
  Player createPlayer(String name);

  Optional<Player> getPlayer(long id);
}

package com.cricket.services.impl;

import java.util.Optional;

import com.cricket.entitymodels.Player;
import com.cricket.repository.PlayerRepository;
import com.cricket.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
  private PlayerRepository playerRepository;

  @Autowired
  public PlayerServiceImpl(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public Player createPlayer(String name) {
    Player p = new Player();
    p.setName(name);
    p = playerRepository.save(p);
    return p;
  }

  @Override
  public Optional<Player> getPlayer(long id) {
    return playerRepository.findById(id);
  }
}

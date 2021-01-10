package com.cricket.repository;

import java.util.List;

import com.cricket.entitymodels.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
  List<Player> findByNameIn(List<String> names);
}

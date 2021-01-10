package com.cricket.repository;

import java.util.List;

import com.cricket.entitymodels.BattingOrder;
import com.cricket.entitymodels.PlayerBattingScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerBattingScoreRepository extends JpaRepository<PlayerBattingScore, Long> {
  @Query(value = "SELECT * FROM player_batting_score WHERE match_id = ?1 and team_id = ?2 and player_id in(?3)", nativeQuery = true)
  List<PlayerBattingScore> findByScorePlayerIds(Long matchId, Long teamId, List<Long> players);

  @Query(value = "SELECT * FROM player_batting_score WHERE match_id = ?1 and team_id = ?2 and out != 1 order by strike", nativeQuery = true)
  List<PlayerBattingScore> findOnStrikePlayers(Long matchId, Long teamId);

  @Query(value = "SELECT count(*) FROM player_batting_score WHERE match_id = ?1 and team_id = ?2 and out = 1", nativeQuery = true)
  int getTotalOutPlayers(Long matchId, Long teamId);

  @Query(value = "SELECT * FROM player_batting_score WHERE match_id = ?1", nativeQuery = true)
  List<PlayerBattingScore> findByMatchId(Long matchId);
}

package com.cricket.repository;

import com.cricket.entitymodels.MatchBattingScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatchBattingScoreRepository extends JpaRepository<MatchBattingScore, Long> {
  @Query(value = "SELECT * FROM match_batting_score WHERE match_id = ?1 and team_id = ?2", nativeQuery = true)
  MatchBattingScore findByMatchIdTeamId(Long matchId, Long teamId);
}

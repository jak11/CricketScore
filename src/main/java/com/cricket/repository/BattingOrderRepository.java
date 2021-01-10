package com.cricket.repository;

import java.util.List;

import com.cricket.entitymodels.BattingOrder;
import com.cricket.entitymodels.MatchTeamRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BattingOrderRepository extends JpaRepository<BattingOrder, Long> {
  @Query(value = "SELECT * FROM batting_order WHERE match_id = ?1 and team_id = ?2 order by sequence", nativeQuery = true)
  List<BattingOrder> findUserByStatusAndName(Long matchId, Long teamId);


  //List<BattingOrder> findByMatchIdTeamId(Long matchId, Long teamId);
}

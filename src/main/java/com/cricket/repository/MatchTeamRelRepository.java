package com.cricket.repository;

import java.util.List;

import com.cricket.entitymodels.Match;
import com.cricket.entitymodels.MatchTeamId;
import com.cricket.entitymodels.MatchTeamRel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchTeamRelRepository extends JpaRepository<MatchTeamRel, MatchTeamId> {
  List<MatchTeamRel> findByMatchTeamIdMatchId(Long matchId);

  List<MatchTeamRel> findByMatchTeamIdTeamId(Long teamId);
}

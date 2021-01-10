package com.cricket.services;

import java.util.List;

import com.cricket.entitymodels.Match;
import com.cricket.entitymodels.Team;
import com.cricket.models.MatchInputRequest;
import com.cricket.models.MatchStatus;

public interface MatchService {
  Match createMatch(MatchInputRequest matchInputRequest);
  Match updateMatchStatus(Long matchId, MatchStatus matchStatus);
}

package com.cricket.services.impl;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.cricket.entitymodels.Match;
import com.cricket.entitymodels.MatchTeamId;
import com.cricket.entitymodels.MatchTeamRel;
import com.cricket.entitymodels.Team;
import com.cricket.models.MatchInputRequest;
import com.cricket.models.MatchStatus;
import com.cricket.repository.MatchRepository;
import com.cricket.repository.MatchTeamRelRepository;
import com.cricket.repository.TeamRepository;
import com.cricket.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {
  private MatchRepository matchRepository;
  private TeamRepository teamRepository;
  private MatchTeamRelRepository matchTeamRelRepository;

  @Autowired
  public MatchServiceImpl(MatchRepository matchRepository, TeamRepository teamRepository, MatchTeamRelRepository matchTeamRelRepository) {
    this.matchRepository = matchRepository;
    this.teamRepository = teamRepository;
    this.matchTeamRelRepository = matchTeamRelRepository;
  }

  @Override
  public Match createMatch(MatchInputRequest matchInputRequest) {
    //Validators for teams
    List<Team> teamList = teamRepository.findAllById(matchInputRequest.getTeams());
    if(teamList.size() != matchInputRequest.getTeams().size()){
      throw new RuntimeException("Invalid teams supplied");
    }

    Match match = new Match();
    match.setStatus(MatchStatus.IN_PROGRESS);
    match.setOversPerTeam(matchInputRequest.getNoOfOvers());
    match.setPlayersPerTeam(matchInputRequest.getNoOfPlayersPerTeam());
    match = matchRepository.save(match);

    Set<MatchTeamRel> matchTeamRelSet = new HashSet<>();

    for (Team team: teamList) {
      MatchTeamRel matchTeamRel = new MatchTeamRel();
      MatchTeamId matchTeamId = new MatchTeamId();
      matchTeamId.setMatchId(match.getId());
      matchTeamId.setTeamId(team.getId());
      matchTeamRel.setMatchTeamId(matchTeamId);
      matchTeamRelSet.add(matchTeamRel);
    }

    matchTeamRelRepository.saveAll(matchTeamRelSet);
    return match;
  }

  @Override
  public Match updateMatchStatus(Long matchId, MatchStatus matchStatus) {
    Optional<Match> optionalMatch = matchRepository.findById(matchId);
    Match match = optionalMatch.orElseThrow(() -> new InvalidParameterException("Provided Match is not present"));
    match.setStatus(matchStatus);
    return matchRepository.save(match);
  }
}

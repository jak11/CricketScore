package com.cricket.controllers;

import javax.validation.constraints.NotNull;

import com.cricket.entitymodels.Match;
import com.cricket.models.MatchInputRequest;
import com.cricket.models.MatchStatus;
import com.cricket.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class MatchController {
  @Autowired
  private MatchService matchService;

  @PostMapping("/match")
  public EntityModel<Match> createPlayer(@RequestBody @NotNull MatchInputRequest inputRequest) {
    return EntityModel.of(matchService.createMatch(inputRequest));
  }

  @PostMapping("/match/{matchId}/start")
  public EntityModel<Match> createPlayer(@PathVariable @NotNull Long matchId) {
    return EntityModel.of(matchService.updateMatchStatus(matchId, MatchStatus.IN_PROGRESS));
  }
}

package com.cricket.controllers;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import com.cricket.entitymodels.Team;
import com.cricket.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class TeamController {
  @Autowired
  private TeamService teamService;

  @GetMapping("/teams/{id}")
  public EntityModel<Team> createPlayer(@PathVariable @NotNull int id) {
    Optional<Team> team = teamService.getTeam(id);
    return team.map(EntityModel::of).orElseThrow(() -> new RuntimeException("Player doesn't exists with provided Id"));
  }
}

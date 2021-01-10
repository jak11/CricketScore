package com.cricket.controllers;

import javax.validation.constraints.NotNull;

import java.util.Optional;

import com.cricket.entitymodels.Player;
import com.cricket.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class PlayerController {
  @Autowired
  private PlayerService playerService;

  @PostMapping("/players")
  public EntityModel<Player> createPlayer(@RequestBody @NotNull Player inputRequest) {
    Player createdPlayer = playerService.createPlayer(inputRequest.getName());
    return EntityModel.of(createdPlayer);
  }

  @GetMapping("/players/{id}")
  public EntityModel<Player> createPlayer(@PathVariable @NotNull Long id) {
    Optional<Player> player = playerService.getPlayer(id);
    return player.map(EntityModel::of).orElseThrow(() -> new RuntimeException("Player doesn't exists with provided Id"));
  }
}

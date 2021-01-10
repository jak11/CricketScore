package com.cricket.controllers;

import javax.validation.constraints.NotNull;

import com.cricket.models.BattingOrderRequest;
import com.cricket.models.ScoreCard;
import com.cricket.services.BattingOrderService;
import com.cricket.services.ScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class ScoreCardController {
  @Autowired
  private ScoreCardService scoreCardService;

  @GetMapping("/scorecard/{matchId}")
  public ResponseEntity<ScoreCard> getScoreCard(@PathVariable @NotNull Long matchId) {
    ScoreCard scoreCard = scoreCardService.getScoreCard(matchId);
    return new ResponseEntity<ScoreCard>(scoreCard, HttpStatus.OK);
  }
}

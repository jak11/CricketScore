package com.cricket.controllers;

import javax.validation.constraints.NotNull;

import com.cricket.models.BattingOrderRequest;
import com.cricket.models.OverInputRequest;
import com.cricket.services.BattingOrderService;
import com.cricket.services.OverProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class OverController {
  @Autowired
  private OverProcessorService overProcessorService;

  @PostMapping("/overs/process")
  public ResponseEntity<String> createPlayer(@RequestBody @NotNull OverInputRequest inputRequest) {
    overProcessorService.processOver(inputRequest);
    return new ResponseEntity<String>(HttpStatus.OK);
  }
}

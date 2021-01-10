package com.cricket.controllers;

import javax.validation.constraints.NotNull;

import com.cricket.models.BattingOrderRequest;
import com.cricket.services.BattingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class BattingController {
  @Autowired
  private BattingOrderService battingOrderService;

  @PostMapping("/batting/order")
  public ResponseEntity<String> createPlayer(@RequestBody @NotNull BattingOrderRequest inputRequest) {
    battingOrderService.saveBattingOrder(inputRequest);
    return new ResponseEntity<String>(HttpStatus.OK);
  }
}

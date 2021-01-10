package com.cricket.services;

import org.springframework.stereotype.Component;

@Component
public class StrikeCalculator {
  public boolean isChangeOfStrike(int run){
    if(run %2 == 0) return false;
    return true;
  }
}

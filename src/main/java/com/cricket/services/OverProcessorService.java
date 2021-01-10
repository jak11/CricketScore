package com.cricket.services;

import java.util.List;

import com.cricket.entitymodels.BattingOrder;
import com.cricket.models.BattingOrderRequest;
import com.cricket.models.OverInputRequest;

public interface OverProcessorService {
  void processOver(OverInputRequest overInputRequest);
}

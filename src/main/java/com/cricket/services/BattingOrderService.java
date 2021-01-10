package com.cricket.services;

import java.util.List;

import com.cricket.entitymodels.BattingOrder;
import com.cricket.models.BattingOrderRequest;

public interface BattingOrderService {
  List<BattingOrder> saveBattingOrder(BattingOrderRequest battingOrderRequest);
}

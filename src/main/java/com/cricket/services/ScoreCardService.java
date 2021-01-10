package com.cricket.services;

import com.cricket.models.ScoreCard;

public interface ScoreCardService {
  ScoreCard getScoreCard(Long matchId);
}

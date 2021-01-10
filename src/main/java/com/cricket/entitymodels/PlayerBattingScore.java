package com.cricket.entitymodels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "player_batting_score")
@JsonIgnoreProperties({"team", "match", "player.id"})
public class PlayerBattingScore {
  private int total;
  private int fours;
  private int threes;
  private int balls;
  private int strike;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "match_id", nullable = false)
  private Match match;

  @OneToOne
  @JoinColumn(name = "team_id", nullable = false)
  private Team team;

  @OneToOne
  @JoinColumn(name = "player_id", nullable = false)
  private Player player;

  private boolean out;

  public int getStrike() {
    return strike;
  }

  public void setStrike(int strike) {
    this.strike = strike;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getFours() {
    return fours;
  }

  public void setFours(int fours) {
    this.fours = fours;
  }

  public int getThrees() {
    return threes;
  }

  public void setThrees(int threes) {
    this.threes = threes;
  }

  public int getBalls() {
    return balls;
  }

  public void setBalls(int balls) {
    this.balls = balls;
  }

  public Match getMatch() {
    return match;
  }

  public void setMatch(Match match) {
    this.match = match;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public boolean isOut() {
    return out;
  }

  public void setOut(boolean out) {
    this.out = out;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlayerBattingScore that = (PlayerBattingScore)o;
    return total == that.total && fours == that.fours && threes == that.threes && balls == that.balls && strike == that.strike && out == that.out && Objects.equals(id, that.id) && Objects.equals(match, that.match) && Objects.equals(team, that.team) && Objects.equals(player, that.player);
  }

  @Override
  public int hashCode() {
    return Objects.hash(total, fours, threes, balls, strike, id, match, team, player, out);
  }
}

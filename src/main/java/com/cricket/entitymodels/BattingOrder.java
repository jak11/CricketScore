package com.cricket.entitymodels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "batting_order")
public class BattingOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "team_id", nullable = false)
  private Team team;

  @ManyToOne
  @JoinColumn(name = "player_id", nullable = false)
  private Player player;

  @ManyToOne
  @JoinColumn(name = "match_id", nullable = false)
  private Match match;

  private int sequence;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public Match getMatch() {
    return match;
  }

  public void setMatch(Match match) {
    this.match = match;
  }

  public int getSequence() {
    return sequence;
  }

  public void setSequence(int sequence) {
    this.sequence = sequence;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BattingOrder that = (BattingOrder)o;
    return sequence == that.sequence && Objects.equals(id, that.id) && Objects.equals(team, that.team) && Objects.equals(player, that.player) && Objects.equals(match, that.match);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, team, player, match, sequence);
  }
}

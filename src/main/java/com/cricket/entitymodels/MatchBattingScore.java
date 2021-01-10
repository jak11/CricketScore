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
@Table(name = "match_batting_score")
@JsonIgnoreProperties("team")
public class MatchBattingScore {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "match_id", nullable = false)
  private Match match;

  @OneToOne
  @JoinColumn(name = "team_id", nullable = false)
  private Team team;

  private int total;
  private int wickets;
  private int overs;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getWickets() {
    return wickets;
  }

  public void setWickets(int wickets) {
    this.wickets = wickets;
  }

  public int getOvers() {
    return overs;
  }

  public void setOvers(int overs) {
    this.overs = overs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MatchBattingScore that = (MatchBattingScore)o;
    return Objects.equals(id, that.id) && Objects.equals(match, that.match) && Objects.equals(team, that.team);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, match, team);
  }
}

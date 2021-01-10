package com.cricket.entitymodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

import com.cricket.models.MatchStatus;

@Entity
@Table(name = "matches")
public class Match {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "status")
  private MatchStatus status;
  @Column(name = "overs_per_team")
  private int oversPerTeam;
  @Column(name = "players_per_team")
  private int playersPerTeam;


  public Long getId() {
    return id;
  }

  public MatchStatus getStatus() {
    return status;
  }

  public void setStatus(MatchStatus status) {
    this.status = status;
  }

  public int getOversPerTeam() {
    return oversPerTeam;
  }

  public void setOversPerTeam(int oversPerTeam) {
    this.oversPerTeam = oversPerTeam;
  }

  public int getPlayersPerTeam() {
    return playersPerTeam;
  }

  public void setPlayersPerTeam(int playersPerTeam) {
    this.playersPerTeam = playersPerTeam;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Match match = (Match)o;
    return oversPerTeam == match.oversPerTeam && playersPerTeam == match.playersPerTeam && Objects.equals(id, match.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, oversPerTeam, playersPerTeam);
  }
}

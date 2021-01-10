package com.cricket.entitymodels;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "match_team_rel")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class MatchTeamRel implements Serializable {
  @EmbeddedId
  private MatchTeamId matchTeamId;

  public MatchTeamId getMatchTeamId() {
    return matchTeamId;
  }

  public void setMatchTeamId(MatchTeamId matchTeamId) {
    this.matchTeamId = matchTeamId;
  }
}

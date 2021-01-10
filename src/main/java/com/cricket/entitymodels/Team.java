package com.cricket.entitymodels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "teams")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToMany(mappedBy="team")
  private Set<Player> players;

  private String name;

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void addPlayer(Player player){
    this.players.add(player);
  }

  public void addPlayer(List<Player> players){
    this.players.addAll(players);
  }

  public List<Player> getPlayers() {
    return new ArrayList<>(this.players);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Team team = (Team)o;
    return id == team.id && Objects.equals(name, team.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}

package com.cricket.repository;

import java.util.List;
import java.util.Set;

import com.cricket.entitymodels.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}

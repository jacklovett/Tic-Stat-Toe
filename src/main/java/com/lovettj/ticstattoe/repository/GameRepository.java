package com.lovettj.ticstattoe.repository;

import org.springframework.stereotype.Repository;

import com.lovettj.ticstattoe.model.Game;
import com.lovettj.ticstattoe.responses.Stats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
  @Query(nativeQuery = true)
  Stats getStats();

}

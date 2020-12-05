package com.lovettj.ticstattoe.repository;

import org.springframework.stereotype.Repository;

import com.lovettj.ticstattoe.model.Game;
import com.lovettj.ticstattoe.responses.Stats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

  @Query("SELECT new com.lovettj.ticstattoe.responses.Stats(count(*) AS gameCount, (SELECT count(*) from Game WHERE winner = 'X') AS xWinnerCount, (SELECT count(*) from Game WHERE winner = 'O') AS oWinnerCount, (SELECT count(*) from Game WHERE winner = 'DRAW') AS drawCount) FROM Game g")

  Stats getStatistic();
}

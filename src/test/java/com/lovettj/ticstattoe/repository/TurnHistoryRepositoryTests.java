package com.lovettj.ticstattoe.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lovettj.ticstattoe.enums.Square;
import com.lovettj.ticstattoe.enums.Winner;
import com.lovettj.ticstattoe.model.Turn;
import com.lovettj.ticstattoe.model.Game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class TurnRepositoryTests {

  private static final Square[] SQUARE_ARRAY = { Square.X, null, null, Square.O, null, null, null, null, Square.X };

  private Game game;
  private List<Square> squares;
  private Turn turn;

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private TurnRepository turnRepository;

  @BeforeEach
  void init() {

    game = new Game();
    game.setStart(Instant.now());
    game.setEnd(Instant.now());
    game.setWinner(Winner.X);

    squares = new ArrayList<Square>();
    Collections.addAll(squares, SQUARE_ARRAY);

    turn = new Turn(game, 1, 0, squares.toString());

  }

  @Test
  void saveShouldSaveTurnSuccessfully() {
    turnRepository.save(turn);
    assertNotNull(turn.getId());
  }

  @Test
  void findAllShouldReturnListOfTurn() {

    entityManager.persist(game);
    entityManager.persist(turn);
    entityManager.flush();

    List<Turn> foundboardHistory = turnRepository.findAll();

    assertEquals(squares.toString(), foundboardHistory.get(0).getBoardHistory());
  }

}

package com.lovettj.ticstattoe.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class GameRepositoryTests {

  private static final Square[] SQUARE_ARRAY = { Square.X, null, null, Square.O, null, null, null, null, Square.X };
  private static final String SELECTED_SQUARE = "a1";

  private Game game;
  private List<Turn> history;

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private GameRepository gameRepository;

  @BeforeEach
  void init() {

    game = new Game();
    game.setStart(Instant.now());
    game.setEnd(Instant.now());
    game.setWinner(Winner.X);

    List<Square> squares = new ArrayList<Square>();
    Collections.addAll(squares, SQUARE_ARRAY);

    Turn turn = new Turn(game, 1, SELECTED_SQUARE, squares.toString());

    history = new ArrayList<Turn>();
    history.add(turn);

    game.setTurns(history);

  }

  @Test
  void saveShouldSaveGameSuccessfully() {
    gameRepository.save(game);
    assertNotNull(game.getId());
  }

  @Test
  void findAllShouldReturnListOfGames() {

    entityManager.persist(game);
    entityManager.flush();

    List<Game> foundGames = gameRepository.findAll();

    assertEquals(Winner.X.toString(), foundGames.get(0).getWinner());
    assertEquals(history, foundGames.get(0).getTurns());
  }
}

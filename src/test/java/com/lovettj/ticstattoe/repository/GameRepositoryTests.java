package com.lovettj.ticstattoe.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lovettj.ticstattoe.enums.Square;
import com.lovettj.ticstattoe.enums.Winner;
import com.lovettj.ticstattoe.model.BoardHistory;
import com.lovettj.ticstattoe.model.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class GameRepositoryTests {

  private static final Square[] SQUARE_ARRAY = { Square.X, null, null, Square.O, null, null, null, null, Square.X };

  private Game game;
  private List<BoardHistory> history;

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private GameRepository gameRepository;

  @BeforeEach
  public void init() {

    game = new Game();
    game.setStart(Instant.now());
    game.setEnd(Instant.now());
    game.setWinner(Winner.X);

    List<Square> squares = new ArrayList<Square>();
    Collections.addAll(squares, SQUARE_ARRAY);

    BoardHistory boardHistory = new BoardHistory(game, 1, squares.toString());

    history = new ArrayList<BoardHistory>();
    history.add(boardHistory);

    game.setBoardHistory(history);

  }

  @Test
  public void saveShouldSaveGameSuccessfully() {
    gameRepository.save(game);
    assertNotNull(game.getId());
  }

  @Test
  public void findAllShouldReturnListOfGames() {

    entityManager.persist(game);
    entityManager.flush();

    List<Game> foundGames = gameRepository.findAll();

    assertEquals(Winner.X.toString(), foundGames.get(0).getWinner());
    assertEquals(history, foundGames.get(0).getBoardHistory());
  }

}

package com.lovettj.ticstattoe.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lovettj.ticstattoe.enums.Square;
import com.lovettj.ticstattoe.enums.Winner;
import com.lovettj.ticstattoe.model.BoardHistory;
import com.lovettj.ticstattoe.model.Game;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class GameRepositoryTests {

  private static final Square[] SQUARE_ARRAY = { Square.X, null, null, Square.O, null, null, null, null, Square.X };

  private Game game;
  private List<Square> squares;
  private List<BoardHistory> history;

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private GameRepository gameRepository;

  @Before
  public void setUp() {

    game = new Game();
    game.setStart(Instant.now());
    game.setEnd(Instant.now());
    game.setWinner(Winner.X);

    squares = new ArrayList<Square>();
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

    List<Game> foundGames = gameRepository.findAll();

    assertNotNull(foundGames);
    assertFalse(foundGames.isEmpty());
    assertEquals(Winner.X.toString(), foundGames.get(0).getWinner());
    assertEquals(history, foundGames.get(0).getBoardHistory());
  }

}

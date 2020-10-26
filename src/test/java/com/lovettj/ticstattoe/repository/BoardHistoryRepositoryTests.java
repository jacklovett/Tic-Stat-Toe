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
public class BoardHistoryRepositoryTests {

  private static final Square[] SQUARE_ARRAY = { Square.X, null, null, Square.O, null, null, null, null, Square.X };

  private Game game;
  private List<Square> squares;
  private BoardHistory boardHistory;

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private BoardHistoryRepository boardHistoryRepository;

  @Before
  public void setUp() {

    game = new Game();
    game.setStart(Instant.now());
    game.setEnd(Instant.now());
    game.setWinner(Winner.X);

    squares = new ArrayList<Square>();
    Collections.addAll(squares, SQUARE_ARRAY);

    boardHistory = new BoardHistory(game, 1, squares.toString());

  }

  @Test
  public void saveShouldSaveBoardHistorySuccessfully() {
    boardHistoryRepository.save(boardHistory);
    assertNotNull(boardHistory.getId());
  }

  @Test
  public void findAllShouldReturnListOfBoardHistory() {

    entityManager.persist(game);
    entityManager.persist(boardHistory);
    entityManager.flush();

    List<BoardHistory> foundboardHistory = boardHistoryRepository.findAll();

    assertNotNull(foundboardHistory);
    assertFalse(foundboardHistory.isEmpty());
    assertEquals(squares.toString(), foundboardHistory.get(0).getSquares());
  }

}
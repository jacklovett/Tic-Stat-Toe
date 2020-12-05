package com.lovettj.ticstattoe.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lovettj.ticstattoe.enums.Square;
import com.lovettj.ticstattoe.enums.Winner;
import com.lovettj.ticstattoe.model.BoardHistory;
import com.lovettj.ticstattoe.model.Game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class BoardHistoryRepositoryTests {

  private static final Square[] SQUARE_ARRAY = { Square.X, null, null, Square.O, null, null, null, null, Square.X };

  private Game game;
  private List<Square> squares;
  private BoardHistory boardHistory;

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private BoardHistoryRepository boardHistoryRepository;

  @BeforeEach
  void init() {

    game = new Game();
    game.setStart(Instant.now());
    game.setEnd(Instant.now());
    game.setWinner(Winner.X);

    squares = new ArrayList<Square>();
    Collections.addAll(squares, SQUARE_ARRAY);

    boardHistory = new BoardHistory(game, 1, squares.toString());

  }

  @Test
  void saveShouldSaveBoardHistorySuccessfully() {
    boardHistoryRepository.save(boardHistory);
    assertNotNull(boardHistory.getId());
  }

  @Test
  void findAllShouldReturnListOfBoardHistory() {

    entityManager.persist(game);
    entityManager.persist(boardHistory);
    entityManager.flush();

    List<BoardHistory> foundboardHistory = boardHistoryRepository.findAll();

    assertEquals(squares.toString(), foundboardHistory.get(0).getSquares());
  }

}

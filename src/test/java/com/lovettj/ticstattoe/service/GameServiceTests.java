package com.lovettj.ticstattoe.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.lovettj.ticstattoe.enums.Square;
import com.lovettj.ticstattoe.enums.Winner;
import com.lovettj.ticstattoe.model.BoardHistory;
import com.lovettj.ticstattoe.model.Game;
import com.lovettj.ticstattoe.repository.BoardHistoryRepository;
import com.lovettj.ticstattoe.repository.GameRepository;
import com.lovettj.ticstattoe.requests.BoardHistoryRequest;
import com.lovettj.ticstattoe.requests.GameRequest;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTests {

  private static final Long ID = 1l;
  private static final Square[] SQUARE_ARRAY = { Square.X, null, null, Square.O, null, null, null, null, Square.X };

  private Game game;
  private GameRequest gameRequest;
  private List<BoardHistoryRequest> boardHistoryRequests;

  @Mock
  private GameRepository gameRepository;
  @Mock
  private BoardHistoryRepository boardHistoryRepository;
  @InjectMocks
  private GameService gameService;

  @Before
  public void setUp() {

    List<Square> squares = new ArrayList<Square>();
    Collections.addAll(squares, SQUARE_ARRAY);

    BoardHistoryRequest boardHistoryRequest = new BoardHistoryRequest();
    boardHistoryRequest.setSquares(squares);

    boardHistoryRequests = new ArrayList<BoardHistoryRequest>();
    boardHistoryRequests.add(boardHistoryRequest);
    boardHistoryRequests.add(boardHistoryRequest);

    Instant start = Instant.now();
    Instant end = Instant.now();

    gameRequest = new GameRequest();
    gameRequest.setStart(start);
    gameRequest.setEnd(end);
    gameRequest.setWinner(Winner.X);
    gameRequest.setBoardHistory(boardHistoryRequests);

    game = new Game();
    game.setStart(start);
    game.setEnd(end);
    game.setWinner(Winner.X);

  }

  @Test
  public void shouldSaveWithValidGameRequest() {

    doAnswer((Answer<Game>) g -> {
      game.setId(ID);
      return game;
    }).when(gameRepository).save(any(Game.class));

    gameService.save(gameRequest);

    verify(boardHistoryRepository, times(2)).save(any(BoardHistory.class));
  }
}

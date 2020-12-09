package com.lovettj.ticstattoe.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.lovettj.ticstattoe.enums.Square;
import com.lovettj.ticstattoe.enums.Winner;
import com.lovettj.ticstattoe.model.Turn;
import com.lovettj.ticstattoe.model.Game;
import com.lovettj.ticstattoe.repository.TurnRepository;
import com.lovettj.ticstattoe.repository.GameRepository;
import com.lovettj.ticstattoe.requests.TurnRequest;
import com.lovettj.ticstattoe.requests.GameRequest;

@ExtendWith(MockitoExtension.class)
class GameServiceTests {

  private static final Long ID = 1l;
  private static final Square[] SQUARE_ARRAY = { Square.X, null, null, Square.O, null, null, null, null, Square.X };
  private static final String SELECTED_SQUARE = "a1";

  private Game game;
  private GameRequest gameRequest;

  @Mock
  private GameRepository gameRepository;
  @Mock
  private TurnRepository turnRepository;
  @InjectMocks
  private GameService gameService;

  @BeforeEach
  void init() {

    List<Square> squares = new ArrayList<Square>();
    Collections.addAll(squares, SQUARE_ARRAY);

    TurnRequest turnRequest = new TurnRequest();
    turnRequest.setBoardHistory(squares);
    turnRequest.setSelectedSquare(SELECTED_SQUARE);

    List<TurnRequest> turnRequests = new ArrayList<TurnRequest>();
    turnRequests.add(turnRequest);
    turnRequests.add(turnRequest);

    Instant start = Instant.now();
    Instant end = Instant.now();

    gameRequest = new GameRequest();
    gameRequest.setStart(start);
    gameRequest.setEnd(end);
    gameRequest.setWinner(Winner.X);
    gameRequest.setTurns(turnRequests);

    game = new Game();
    game.setStart(start);
    game.setEnd(end);
    game.setWinner(Winner.X);

  }

  @Test
  void shouldSaveWithValidGameRequest() {

    doAnswer((Answer<Game>) g -> {
      game.setId(ID);
      return game;
    }).when(gameRepository).save(any(Game.class));

    gameService.save(gameRequest);

    verify(turnRepository, times(2)).save(any(Turn.class));
  }
}

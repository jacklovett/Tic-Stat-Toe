package com.lovettj.ticstattoe.service;

import java.util.List;
import java.util.stream.IntStream;

import com.google.gson.Gson;
import com.lovettj.ticstattoe.enums.Square;
import com.lovettj.ticstattoe.model.Turn;
import com.lovettj.ticstattoe.model.Game;
import com.lovettj.ticstattoe.repository.GameRepository;
import com.lovettj.ticstattoe.repository.TurnRepository;
import com.lovettj.ticstattoe.requests.TurnRequest;
import com.lovettj.ticstattoe.responses.Stats;
import com.lovettj.ticstattoe.requests.GameRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  private Gson gson = new Gson();

  @Autowired
  private GameRepository gameRepository;

  @Autowired
  private TurnRepository turnRepository;

  public Stats getStatistics() {
    return gameRepository.getStats();
  }

  public void save(GameRequest gameRequest) {
    Game savedGame = saveGame(gameRequest);

    List<TurnRequest> turns = gameRequest.getTurns();

    saveTurns(turns, savedGame);
  }

  private Game saveGame(GameRequest gameRequest) {
    Game game = new Game(gameRequest);
    // TODO: Cancel all if one addition to database fails - @Transactional ?
    return gameRepository.save(game);
  }

  private void saveTurns(List<TurnRequest> turns, Game savedGame) {
    IntStream.range(0, turns.size()).forEach(index -> saveTurn(savedGame, index, turns.get(index)));
  }

  private void saveTurn(Game savedGame, int index, TurnRequest turnRequest) {
    List<Square> boardHistory = turnRequest.getBoardHistory();
    String boardHistoryJSON = gson.toJson(boardHistory);
    Turn turn = new Turn(savedGame, index, turnRequest.getSelectedSquare(), boardHistoryJSON);
    turnRepository.save(turn);
  }

}

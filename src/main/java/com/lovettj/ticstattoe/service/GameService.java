package com.lovettj.ticstattoe.service;

import java.util.List;

import com.google.gson.Gson;
import com.lovettj.ticstattoe.enums.Square;
import com.lovettj.ticstattoe.model.BoardHistory;
import com.lovettj.ticstattoe.model.Game;
import com.lovettj.ticstattoe.repository.BoardHistoryRepository;
import com.lovettj.ticstattoe.repository.GameRepository;
import com.lovettj.ticstattoe.requests.BoardHistoryRequest;
import com.lovettj.ticstattoe.requests.GameRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  @Autowired
  private Gson gson;

  @Autowired
  private GameRepository gameRepository;

  @Autowired
  private BoardHistoryRepository boardHistoryRepository;

  public void save(GameRequest gameRequest) {
    Game savedGame = saveGame(gameRequest);

    List<BoardHistoryRequest> boardHistory = gameRequest.getBoardHistory();

    saveBoardHistory(boardHistory, savedGame);

  }

  private Game saveGame(GameRequest gameRequest) {
    Game game = new Game(gameRequest);
    // TODO: Cancel all if one addition to database fails - @Transactional ?
    return gameRepository.save(game);
  }

  private void saveBoardHistory(List<BoardHistoryRequest> boardHistory, Game savedGame) {
    for (int index = 0; index < boardHistory.size(); index++) {

      List<Square> turnHistory = boardHistory.get(index).getSquares();
      String turnHistoryJSON = gson.toJson(turnHistory);

      boardHistoryRepository.save(new BoardHistory(savedGame, index, turnHistoryJSON));
    }

  }

}

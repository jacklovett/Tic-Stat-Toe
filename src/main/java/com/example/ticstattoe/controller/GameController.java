package com.example.ticstattoe.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.ticstattoe.enums.Square;
import com.example.ticstattoe.model.BoardHistory;
import com.example.ticstattoe.model.Game;
import com.example.ticstattoe.repository.BoardHistoryRepository;
import com.example.ticstattoe.repository.GameRepository;
import com.example.ticstattoe.requests.GameRequest;
import com.google.gson.Gson;
import com.example.ticstattoe.requests.BoardHistoryRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GameController {

  @Autowired
  private Gson gson;

  @Autowired
  private GameRepository gameRepository;

  @Autowired
  private BoardHistoryRepository boardHistoryRepository;

  @PostMapping("/game")
  public ResponseEntity<String> saveGame(@Valid @RequestBody GameRequest gameRequest) {
    Game game = new Game(gameRequest);
    // TODO: Cancel all if one addition to database fails - @Transactional ?

    Game savedGame = gameRepository.save(game);

    List<BoardHistoryRequest> boardHistory = gameRequest.getBoardHistory();

    for (int index = 0; index < boardHistory.size(); index++) {

      List<Square> turnHistory = boardHistory.get(index).getSquares();
      String turnHistoryJSON = gson.toJson(turnHistory);

      boardHistoryRepository.save(new BoardHistory(savedGame, index, turnHistoryJSON));
    }

    return ResponseEntity.ok("Game results saved successfully!");
  }

  @GetMapping("/games")
  public List<Game> getGames() {
    return gameRepository.findAll();
  }
}

package com.example.ticstattoe.controller;

import javax.validation.Valid;

import com.example.ticstattoe.model.Game;
import com.example.ticstattoe.repository.GameRepository;
import com.example.ticstattoe.requests.GameRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

  @Autowired
  private GameRepository gameRepository;

  private static final Logger logger = LoggerFactory.getLogger(GameController.class);

  @PostMapping("/")
  public void saveGame(@Valid @RequestBody GameRequest gameRequest) {
    logger.info("GameController - saveGame - init");
    Game game = new Game(gameRequest);
    gameRepository.save(game);
  }
}

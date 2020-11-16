package com.lovettj.ticstattoe.controller;

import javax.validation.Valid;

import com.lovettj.ticstattoe.requests.GameRequest;
import com.lovettj.ticstattoe.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GameController {

  @Autowired
  private GameService gameService;

  @PostMapping("/game")
  public ResponseEntity<String> saveGame(@Valid @RequestBody GameRequest gameRequest) {
    gameService.save(gameRequest);
    return ResponseEntity.ok("Game results saved successfully!");
  }

}

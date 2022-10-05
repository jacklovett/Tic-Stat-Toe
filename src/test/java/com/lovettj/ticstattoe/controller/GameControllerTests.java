package com.lovettj.ticstattoe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static java.math.BigDecimal.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lovettj.ticstattoe.enums.Square;
import com.lovettj.ticstattoe.enums.Winner;
import com.lovettj.ticstattoe.requests.GameRequest;
import com.lovettj.ticstattoe.requests.TurnRequest;
import com.lovettj.ticstattoe.responses.Stats;
import com.lovettj.ticstattoe.service.GameService;
import com.lovettj.ticstattoe.utils.InstantConverter;

import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(GameController.class)
class GameControllerTests {

  private static final Square[] SQUARE_ARRAY = { Square.X, null, null, Square.O, null, null, null, null, Square.X };
  private static final String SELECTED_SQUARE = "a1";

  private GameRequest gameRequest;

  private Stats stats;

  private Gson gson;

  @Autowired
  private MockMvc mvc;

  @MockBean
  private GameService gameService;

  @BeforeEach
  void init() {

    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(Instant.class, new InstantConverter());
    gson = gsonBuilder.serializeNulls().create();

    List<Square> squares = new ArrayList<Square>();
    Collections.addAll(squares, SQUARE_ARRAY);

    TurnRequest turnRequest = new TurnRequest();
    turnRequest.setBoardHistory(squares);
    turnRequest.setSelectedSquare(SELECTED_SQUARE);

    List<TurnRequest> turns = new ArrayList<TurnRequest>();
    turns.add(turnRequest);

    gameRequest = new GameRequest();
    gameRequest.setStart(Instant.now());
    gameRequest.setEnd(Instant.now());
    gameRequest.setWinner(Winner.X);
    gameRequest.setTurns(turns);

    stats = new Stats();
  }

  @Test
  void shouldReturn200WhenGameRequestIsValid() throws Exception {

    doNothing().when(gameService).save(gameRequest);

    String gameRequestJson = gson.toJson(gameRequest);

    MvcResult result = mvc.perform(post("/api/game").contentType(MediaType.APPLICATION_JSON).content(gameRequestJson)
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

    MockHttpServletResponse response = result.getResponse();
    assertEquals("Game results saved successfully!", response.getContentAsString());
  }

  @Test
  void shouldReturn400WhenGameRequestIsInValid() throws Exception {
    mvc.perform(
        post("/api/game").contentType(MediaType.APPLICATION_JSON).content("").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void shouldReturn200WhenGetStatsCalled() throws Exception {

    when(gameService.getStatistics()).thenReturn(stats);

    MvcResult result = mvc.perform(get("/api/stats").contentType(MediaType.APPLICATION_JSON)).andReturn();

    MockHttpServletResponse response = result.getResponse();
    JSONAssert.assertEquals("{}", response.getContentAsString(), false);
  }

  @Test
  void shouldReturn200WhenGetStatsWithTimes() throws Exception {
    stats = stats(ONE, new BigDecimal("360000"), ZERO);
    when(gameService.getStatistics()).thenReturn(stats);

    mvc.perform(get("/api/stats").contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.avgGameTime").value("PT1S"))
            .andExpect(jsonPath("$.maxGameTime").value("PT100H"))
            .andExpect(jsonPath("$.minGameTime").value("PT0S"));
  }

  private Stats stats(BigDecimal avgGameTime, BigDecimal maxGameTime, BigDecimal minGameTime) {
    return new Stats(10L, 10L, 10L, 10L, avgGameTime, maxGameTime, minGameTime, "c", "d", "e", "f");
  }

}

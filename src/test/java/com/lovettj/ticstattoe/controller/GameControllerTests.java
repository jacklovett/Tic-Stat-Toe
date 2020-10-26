package com.lovettj.ticstattoe.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lovettj.ticstattoe.enums.Square;
import com.lovettj.ticstattoe.enums.Winner;
import com.lovettj.ticstattoe.requests.BoardHistoryRequest;
import com.lovettj.ticstattoe.requests.GameRequest;
import com.lovettj.ticstattoe.service.GameService;
import com.lovettj.ticstattoe.utils.InstantConverter;

import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTests {

  private static final Square[] SQUARE_ARRAY = { Square.X, null, null, Square.O, null, null, null, null, Square.X };

  private GameRequest gameRequest;

  private Gson gson;

  @Autowired
  private MockMvc mvc;

  @MockBean
  private GameService gameService;

  @Before
  public void setUp() {

    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(Instant.class, new InstantConverter());
    gson = gsonBuilder.create();

    List<Square> squares = new ArrayList<Square>();
    Collections.addAll(squares, SQUARE_ARRAY);

    BoardHistoryRequest boardHistoryRequest = new BoardHistoryRequest();
    boardHistoryRequest.setSquares(squares);

    List<BoardHistoryRequest> boardHistory = new ArrayList<BoardHistoryRequest>();
    boardHistory.add(boardHistoryRequest);

    gameRequest = new GameRequest();
    gameRequest.setStart(Instant.now());
    gameRequest.setEnd(Instant.now());
    gameRequest.setWinner(Winner.X);
    gameRequest.setBoardHistory(boardHistory);
  }

  @Test
  public void shouldReturn200WhenGameRequestIsValid() throws Exception {

    doNothing().when(gameService).save(gameRequest);

    String gameRequestJson = gson.toJson(gameRequest);

    MvcResult result = mvc.perform(post("/api/game").contentType(MediaType.APPLICATION_JSON).content(gameRequestJson)
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

    MockHttpServletResponse response = result.getResponse();
    assertEquals("Game results saved successfully!", response.getContentAsString());
  }

  @Test
  public void shouldReturn400WhenGameRequestIsInValid() throws Exception {
    mvc.perform(
        post("/api/game").contentType(MediaType.APPLICATION_JSON).content("").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

}
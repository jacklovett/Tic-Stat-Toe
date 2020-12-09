package com.lovettj.ticstattoe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author jackl
 *
 */
@Entity
@Table(name = "turns")
public class Turn implements Serializable {

  private static final long serialVersionUID = 6396202134461227456L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "game_id")
  private Game game;

  private int turnNumber;

  private String selectedSquare;

  @NotBlank
  private String boardHistory;

  public Turn(Game game, int turnNumber, String selectedSquare, String boardHistory) {
    this.setGame(game);
    this.setTurnNumber(turnNumber);
    this.setSelectedSquare(selectedSquare);
    this.setBoardHistory(boardHistory);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public String getBoardHistory() {
    return boardHistory;
  }

  public void setBoardHistory(String boardHistory) {
    this.boardHistory = boardHistory;
  }

  public int getTurnNumber() {
    return turnNumber;
  }

  public void setTurnNumber(int turnNumber) {
    this.turnNumber = turnNumber;
  }

  public String getSelectedSquare() {
    return selectedSquare;
  }

  public void setSelectedSquare(String selectedSquare) {
    this.selectedSquare = selectedSquare;
  }

}

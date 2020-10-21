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
@Table(name = "board_history")
public class BoardHistory implements Serializable {

  private static final long serialVersionUID = 6396202134461227456L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "game_id")
  private Game game;

  private int turn;

  @NotBlank
  private String squares;

  public BoardHistory(Game game, int turn, String squares) {
    this.game = game;
    this.turn = turn;
    this.squares = squares;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public String getSquares() {
    return squares;
  }

  public void setSquares(String squares) {
    this.squares = squares;
  }

  public int getTurn() {
    return turn;
  }

  public void setTurn(int turn) {
    this.turn = turn;
  }

}

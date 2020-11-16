package com.lovettj.ticstattoe.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.lovettj.ticstattoe.enums.Winner;
import com.lovettj.ticstattoe.requests.GameRequest;

/**
 * 
 * @author jackl
 *
 */
@Entity
@Table(name = "games")
public class Game implements Serializable {

  private static final long serialVersionUID = 8515800800954257077L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "start_time", nullable = false)
  private Instant start;

  @Column(name = "end_time", nullable = false)
  private Instant end;

  @NotBlank
  @Size(max = 4)
  private String winner;

  @OneToMany(mappedBy = "game", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<BoardHistory> boardHistory;

  public Game() {
  }

  public Game(GameRequest gameRequest) {
    this.setStart(gameRequest.getStart());
    this.setEnd(gameRequest.getEnd());
    this.setWinner(gameRequest.getWinner());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Instant getStart() {
    return start;
  }

  public void setStart(Instant start) {
    this.start = start;
  }

  public Instant getEnd() {
    return end;
  }

  public void setEnd(Instant end) {
    this.end = end;
  }

  public String getWinner() {
    return winner;
  }

  public void setWinner(Winner winner) {
    this.winner = winner.toString();
  }

  public List<BoardHistory> getBoardHistory() {
    return boardHistory;
  }

  public void setBoardHistory(List<BoardHistory> boardHistory) {
    this.boardHistory = boardHistory;
  }

}

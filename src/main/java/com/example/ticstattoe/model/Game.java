package com.example.ticstattoe.model;

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

import com.example.ticstattoe.enums.Winner;
import com.example.ticstattoe.requests.GameRequest;

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

  @NotBlank
  @Column(name = "start_time")
  private Instant start;

  @NotBlank
  @Column(name = "end_time")
  private Instant end;

  @NotBlank
  @Size(max = 4)
  private Winner winner;

  @OneToMany(mappedBy = "game", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<BoardHistory> boardHistory;

  public Game() {
  }

  public Game(GameRequest gameRequest) {
    this.start = gameRequest.getStart();
    this.end = gameRequest.getEnd();
    this.winner = gameRequest.getWinner();
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

  public Winner getWinner() {
    return winner;
  }

  public void setWinner(Winner winner) {
    this.winner = winner;
  }

}

package com.example.ticstattoe.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.ticstattoe.enums.Square;

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

  @ElementCollection
  private List<Square> history;

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public List<Square> getHistory() {
    return history;
  }

  public void setHistory(List<Square> history) {
    this.history = history;
  }
}

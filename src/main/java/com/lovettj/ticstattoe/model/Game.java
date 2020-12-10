package com.lovettj.ticstattoe.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.lovettj.ticstattoe.enums.Winner;
import com.lovettj.ticstattoe.requests.GameRequest;
import com.lovettj.ticstattoe.responses.Stats;

/**
 * 
 * @author jackl
 *
 */
@SqlResultSetMapping(name = "StatsResults", classes = { @ConstructorResult(targetClass = Stats.class, columns = {
    @ColumnResult(name = "gameCount", type = Long.class), @ColumnResult(name = "winnerCountX", type = Long.class),
    @ColumnResult(name = "winnerCountO", type = Long.class), @ColumnResult(name = "drawCount", type = Long.class),
    @ColumnResult(name = "avgGameTime", type = String.class), @ColumnResult(name = "maxGameTime", type = String.class),
    @ColumnResult(name = "minGameTime", type = String.class),
    @ColumnResult(name = "startPositionsX", type = String.class),
    @ColumnResult(name = "startPositionsO", type = String.class) }) })
@NamedNativeQuery(name = "Game.getStats", query = "SELECT count(*) AS gameCount,(SELECT count(*) from games WHERE winner = 'X') AS winnerCountX,(SELECT count(*) from games WHERE winner = 'O') AS winnerCountO,(SELECT count(*) from games WHERE winner = 'DRAW') AS drawCount, CAST(avg(g.end_time - g.start_time) AS text) AS avgGameTime, CAST(max(g.end_time - g.start_time) AS text) AS maxGameTime, CAST(min(g.end_time - g.start_time) AS text) AS minGameTime, (SELECT string_agg(position, ', ') FROM (SELECT t.selected_square AS position, count(*) AS count_num,rank() OVER (ORDER BY count(*) DESC) AS rnk FROM games g JOIN turns t ON t.game_id = g.id WHERE t.turn_number = 0 GROUP BY position) as sub WHERE rnk = 1) AS startPositionsX, (SELECT string_agg(position, ', ') FROM (SELECT t.selected_square AS position, count(*) AS count_num,rank() OVER (ORDER BY count(*) DESC) AS rnk FROM games g JOIN turns t ON t.game_id = g.id WHERE t.turn_number = 1 GROUP BY position) as sub WHERE rnk = 1) AS startPositionsO FROM games g LIMIT 1", resultSetMapping = "StatsResults")
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
  private List<Turn> turns;

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

  public List<Turn> getTurns() {
    return turns;
  }

  public void setTurns(List<Turn> turns) {
    this.turns = turns;
  }

}

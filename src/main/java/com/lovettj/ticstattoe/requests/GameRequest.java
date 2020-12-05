package com.lovettj.ticstattoe.requests;

import java.time.Instant;
import java.util.List;

import com.lovettj.ticstattoe.enums.Winner;

public class GameRequest {

  private List<TurnRequest> turns;

  private Instant start;

  private Instant end;

  private Winner winner;

  public List<TurnRequest> getTurns() {
    return turns;
  }

  public void setTurns(List<TurnRequest> turns) {
    this.turns = turns;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((end == null) ? 0 : end.hashCode());
    result = prime * result + ((start == null) ? 0 : start.hashCode());
    result = prime * result + ((turns == null) ? 0 : turns.hashCode());
    result = prime * result + ((winner == null) ? 0 : winner.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    GameRequest other = (GameRequest) obj;
    if (end == null) {
      if (other.end != null)
        return false;
    } else if (!end.equals(other.end))
      return false;
    if (start == null) {
      if (other.start != null)
        return false;
    } else if (!start.equals(other.start))
      return false;
    if (turns == null) {
      if (other.turns != null)
        return false;
    } else if (!turns.equals(other.turns))
      return false;
    if (winner != other.winner)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "GameRequest [end=" + end + ", start=" + start + ", turns=" + turns + ", winner=" + winner + "]";
  }

}

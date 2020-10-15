package com.example.ticstattoe.requests;

import java.time.Instant;
import java.util.List;

import com.example.ticstattoe.enums.Winner;

public class GameRequest {

  private List<BoardHistoryRequest> boardHistory;

  private Instant start;

  private Instant end;

  private Winner winner;

  public List<BoardHistoryRequest> getBoardHistory() {
    return boardHistory;
  }

  public void setBoardHistory(List<BoardHistoryRequest> boardHistory) {
    this.boardHistory = boardHistory;
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
    result = prime * result + ((boardHistory == null) ? 0 : boardHistory.hashCode());
    result = prime * result + ((end == null) ? 0 : end.hashCode());
    result = prime * result + ((start == null) ? 0 : start.hashCode());
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
    if (boardHistory == null) {
      if (other.boardHistory != null)
        return false;
    } else if (!boardHistory.equals(other.boardHistory))
      return false;
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
    if (winner != other.winner)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "GameRequest [boardHistory=" + boardHistory + ", end=" + end + ", start=" + start + ", winner=" + winner
        + "]";
  }

}

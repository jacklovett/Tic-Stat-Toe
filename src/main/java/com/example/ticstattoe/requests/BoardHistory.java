package com.example.ticstattoe.requests;

import java.util.Arrays;

import com.example.ticstattoe.enums.Square;

public class BoardHistory {

  private Square[] history;

  public Square[] getHistory() {
    return history;
  }

  public void setHistory(Square[] history) {
    this.history = history;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(history);
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
    BoardHistory other = (BoardHistory) obj;
    if (!Arrays.equals(history, other.history))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "BoardHistory [history=" + Arrays.toString(history) + "]";
  }

}

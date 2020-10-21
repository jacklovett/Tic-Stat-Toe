package com.lovettj.ticstattoe.requests;

import java.util.List;

import com.lovettj.ticstattoe.enums.Square;

public class BoardHistoryRequest {

  private List<Square> squares;

  public List<Square> getSquares() {
    return squares;
  }

  public void setSquares(List<Square> squares) {
    this.squares = squares;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((squares == null) ? 0 : squares.hashCode());
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
    BoardHistoryRequest other = (BoardHistoryRequest) obj;
    if (squares == null) {
      if (other.squares != null)
        return false;
    } else if (!squares.equals(other.squares))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "BoardHistoryRequest [squares=" + squares + "]";
  }

}

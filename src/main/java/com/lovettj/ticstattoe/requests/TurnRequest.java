package com.lovettj.ticstattoe.requests;

import java.util.List;

import com.lovettj.ticstattoe.enums.Square;

public class TurnRequest {

  private List<Square> boardHistory;

  private String selectedSquare;

  public List<Square> getBoardHistory() {
    return boardHistory;
  }

  public void setBoardHistory(List<Square> boardHistory) {
    this.boardHistory = boardHistory;
  }

  public String getSelectedSquare() {
    return selectedSquare;
  }

  public void setSelectedSquare(String selectedSquare) {
    this.selectedSquare = selectedSquare;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((boardHistory == null) ? 0 : boardHistory.hashCode());
    result = prime * result + ((selectedSquare == null) ? 0 : selectedSquare.hashCode());
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
    TurnRequest other = (TurnRequest) obj;
    if (boardHistory == null) {
      if (other.boardHistory != null)
        return false;
    } else if (!boardHistory.equals(other.boardHistory))
      return false;
    if (selectedSquare == null) {
      if (other.selectedSquare != null)
        return false;
    } else if (!selectedSquare.equals(other.selectedSquare))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "TurnRequest [boardHistory=" + boardHistory + ", selectedSquare=" + selectedSquare + "]";
  }

}

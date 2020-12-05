package com.lovettj.ticstattoe.responses;

public class Stats {

  private Long gameCount;
  private Long winnerCountX;
  private Long winnerCountO;
  private Long drawCount;

  public Stats() {
  }

  public Stats(Long gameCount, Long winnerCountX, Long winnerCountO, Long drawCount) {
    this.setGameCount(gameCount);
    this.setWinnerCountX(winnerCountX);
    this.setWinnerCountO(winnerCountO);
    this.setDrawCount(drawCount);
  }

  public Long getGameCount() {
    return gameCount;
  }

  public void setGameCount(Long gameCount) {
    this.gameCount = gameCount;
  }

  public Long getWinnerCountX() {
    return winnerCountX;
  }

  public void setWinnerCountX(Long winnerCountX) {
    this.winnerCountX = winnerCountX;
  }

  public Long getWinnerCountO() {
    return winnerCountO;
  }

  public void setWinnerCountO(Long winnerCountO) {
    this.winnerCountO = winnerCountO;
  }

  public Long getDrawCount() {
    return drawCount;
  }

  public void setDrawCount(Long drawCount) {
    this.drawCount = drawCount;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((drawCount == null) ? 0 : drawCount.hashCode());
    result = prime * result + ((gameCount == null) ? 0 : gameCount.hashCode());
    result = prime * result + ((winnerCountO == null) ? 0 : winnerCountO.hashCode());
    result = prime * result + ((winnerCountX == null) ? 0 : winnerCountX.hashCode());
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
    Stats other = (Stats) obj;
    if (drawCount == null) {
      if (other.drawCount != null)
        return false;
    } else if (!drawCount.equals(other.drawCount))
      return false;
    if (gameCount == null) {
      if (other.gameCount != null)
        return false;
    } else if (!gameCount.equals(other.gameCount))
      return false;
    if (winnerCountO == null) {
      if (other.winnerCountO != null)
        return false;
    } else if (!winnerCountO.equals(other.winnerCountO))
      return false;
    if (winnerCountX == null) {
      if (other.winnerCountX != null)
        return false;
    } else if (!winnerCountX.equals(other.winnerCountX))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Stats [drawCount=" + drawCount + ", gameCount=" + gameCount + ", winnerCountO=" + winnerCountO
        + ", winnerCountX=" + winnerCountX + "]";
  }

}

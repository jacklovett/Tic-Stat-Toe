package com.lovettj.ticstattoe.responses;

public class Stats {

  private Long gameCount;
  private Long xWinnerCount;
  private Long oWinnerCount;
  private Long drawCount;

  public Stats() {
  }

  public Stats(Long gameCount, Long xWinnerCount, Long oWinnerCount, Long drawCount) {
    this.setGameCount(gameCount);
    this.setXWinnerCount(xWinnerCount);
    this.setOWinnerCount(oWinnerCount);
    this.setDrawCount(drawCount);
  }

  public Long getGameCount() {
    return gameCount;
  }

  public void setGameCount(Long gameCount) {
    this.gameCount = gameCount;
  }

  public Long getXWinnerCount() {
    return xWinnerCount;
  }

  public void setXWinnerCount(Long xWinnerCount) {
    this.xWinnerCount = xWinnerCount;
  }

  public Long getOWinnerCount() {
    return oWinnerCount;
  }

  public void setOWinnerCount(Long oWinnerCount) {
    this.oWinnerCount = oWinnerCount;
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
    result = prime * result + ((oWinnerCount == null) ? 0 : oWinnerCount.hashCode());
    result = prime * result + ((xWinnerCount == null) ? 0 : xWinnerCount.hashCode());
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
    if (oWinnerCount == null) {
      if (other.oWinnerCount != null)
        return false;
    } else if (!oWinnerCount.equals(other.oWinnerCount))
      return false;
    if (xWinnerCount == null) {
      if (other.xWinnerCount != null)
        return false;
    } else if (!xWinnerCount.equals(other.xWinnerCount))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Stats [drawCount=" + drawCount + ", gameCount=" + gameCount + ", oWinnerCount=" + oWinnerCount
        + ", xWinnerCount=" + xWinnerCount + "]";
  }

}

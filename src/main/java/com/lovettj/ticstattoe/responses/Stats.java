package com.lovettj.ticstattoe.responses;

public class Stats {
  private Long gameCount;
  private Long winnerCountX;
  private Long winnerCountO;
  private Long drawCount;
  private String avgGameTime;
  private String maxGameTime;
  private String minGameTime;
  private String startPositionsX;
  private String startPositionsO;
  private String winningStartPositionsX;
  private String winningStartPositionsO;

  public Stats() {
  }

  public Stats(Long gameCount, Long winnerCountX, Long winnerCountO, Long drawCount, String avgGameTime,
      String maxGameTime, String minGameTime, String startPositionsX, String startPositionsO,
      String winningStartPositionsX, String winningStartPositionsO) {
    this.setGameCount(gameCount);
    this.setWinnerCountX(winnerCountX);
    this.setWinnerCountO(winnerCountO);
    this.setDrawCount(drawCount);
    this.setAvgGameTime(avgGameTime);
    this.setMaxGameTime(maxGameTime);
    this.setMinGameTime(minGameTime);
    this.setStartPositionsX(startPositionsX);
    this.setStartPositionsO(startPositionsO);
    this.setWinningStartPositionsX(winningStartPositionsX);
    this.setWinningStartPositionsO(winningStartPositionsO);
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

  public String getAvgGameTime() {
    return avgGameTime;
  }

  public void setAvgGameTime(String avgGameTime) {
    this.avgGameTime = avgGameTime;
  }

  public String getMaxGameTime() {
    return maxGameTime;
  }

  public void setMaxGameTime(String maxGameTime) {
    this.maxGameTime = maxGameTime;
  }

  public String getMinGameTime() {
    return minGameTime;
  }

  public void setMinGameTime(String minGameTime) {
    this.minGameTime = minGameTime;
  }

  public String getStartPositionsX() {
    return startPositionsX;
  }

  public void setStartPositionsX(String startPositionsX) {
    this.startPositionsX = startPositionsX;
  }

  public String getStartPositionsO() {
    return startPositionsO;
  }

  public void setStartPositionsO(String startPositionsO) {
    this.startPositionsO = startPositionsO;
  }

  public String getWinningStartPositionsX() {
    return winningStartPositionsX;
  }

  public void setWinningStartPositionsX(String winningStartPositionsX) {
    this.winningStartPositionsX = winningStartPositionsX;
  }

  public String getWinningStartPositionsO() {
    return winningStartPositionsO;
  }

  public void setWinningStartPositionsO(String winningStartPositionsO) {
    this.winningStartPositionsO = winningStartPositionsO;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((avgGameTime == null) ? 0 : avgGameTime.hashCode());
    result = prime * result + ((drawCount == null) ? 0 : drawCount.hashCode());
    result = prime * result + ((gameCount == null) ? 0 : gameCount.hashCode());
    result = prime * result + ((maxGameTime == null) ? 0 : maxGameTime.hashCode());
    result = prime * result + ((minGameTime == null) ? 0 : minGameTime.hashCode());
    result = prime * result + ((startPositionsO == null) ? 0 : startPositionsO.hashCode());
    result = prime * result + ((startPositionsX == null) ? 0 : startPositionsX.hashCode());
    result = prime * result + ((winnerCountO == null) ? 0 : winnerCountO.hashCode());
    result = prime * result + ((winnerCountX == null) ? 0 : winnerCountX.hashCode());
    result = prime * result + ((winningStartPositionsO == null) ? 0 : winningStartPositionsO.hashCode());
    result = prime * result + ((winningStartPositionsX == null) ? 0 : winningStartPositionsX.hashCode());
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
    if (avgGameTime == null) {
      if (other.avgGameTime != null)
        return false;
    } else if (!avgGameTime.equals(other.avgGameTime))
      return false;
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
    if (maxGameTime == null) {
      if (other.maxGameTime != null)
        return false;
    } else if (!maxGameTime.equals(other.maxGameTime))
      return false;
    if (minGameTime == null) {
      if (other.minGameTime != null)
        return false;
    } else if (!minGameTime.equals(other.minGameTime))
      return false;
    if (startPositionsO == null) {
      if (other.startPositionsO != null)
        return false;
    } else if (!startPositionsO.equals(other.startPositionsO))
      return false;
    if (startPositionsX == null) {
      if (other.startPositionsX != null)
        return false;
    } else if (!startPositionsX.equals(other.startPositionsX))
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
    if (winningStartPositionsO == null) {
      if (other.winningStartPositionsO != null)
        return false;
    } else if (!winningStartPositionsO.equals(other.winningStartPositionsO))
      return false;
    if (winningStartPositionsX == null) {
      if (other.winningStartPositionsX != null)
        return false;
    } else if (!winningStartPositionsX.equals(other.winningStartPositionsX))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Stats [avgGameTime=" + avgGameTime + ", drawCount=" + drawCount + ", gameCount=" + gameCount
        + ", maxGameTime=" + maxGameTime + ", minGameTime=" + minGameTime + ", startPositionsO=" + startPositionsO
        + ", startPositionsX=" + startPositionsX + ", winnerCountO=" + winnerCountO + ", winnerCountX=" + winnerCountX
        + ", winningStartPositionsO=" + winningStartPositionsO + ", winningStartPositionsX=" + winningStartPositionsX
        + "]";
  }

}

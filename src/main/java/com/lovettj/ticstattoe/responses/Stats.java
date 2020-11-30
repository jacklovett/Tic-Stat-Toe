package com.lovettj.ticstattoe.responses;

public class Stats {

  private Long gameCount;

  public Stats(Long gameCount) {
    this.setGameCount(gameCount);
  }

  public Long getGameCount() {
    return gameCount;
  }

  public void setGameCount(Long gameCount) {
    this.gameCount = gameCount;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((gameCount == null) ? 0 : gameCount.hashCode());
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
    if (gameCount == null) {
      if (other.gameCount != null)
        return false;
    } else if (!gameCount.equals(other.gameCount))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Stats [gameCount=" + gameCount + "]";
  }

}

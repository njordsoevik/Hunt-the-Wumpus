package dungeon.model;

class Otyugh {
  private Health health;

  public Otyugh() {
    this.health = Health.HEALTHY;
  }

  /**
   * Get the health of this player.
   *
   * @return the player health.
   */
  public Health getHealth() {
    return this.health;
  }

  public void reduceHealth() {
    if (this.health == Health.HEALTHY) {
      this.health = Health.INJURED;
    } else {
      this.health = Health.DEAD;
    }
  }
}

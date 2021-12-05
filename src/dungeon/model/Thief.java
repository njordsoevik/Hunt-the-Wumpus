package dungeon.model;

/**
 * Thieves reside alone in the dungeon, in both tunnels and caves. If encountered for the first
 * time, the thief will steal the players treasure.
 */
class Thief {
  private Boolean stolen;

  public Thief() {
    stolen = false;
  }

  public void setStolen(Boolean b) {
    stolen = b;
  }

  public Boolean hasStolen() {
    return stolen;
  }
}

package dungeon.model;

public class Thief {
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

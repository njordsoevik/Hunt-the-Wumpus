import java.util.ArrayList;
import java.util.List;

/**
 * Player to access and navigate the dungeon. Players can also store treasure.
 */
class Player {
  private List<Treasure> treasures;
  private Coordinate coordinate;

  Player(Coordinate coordinate) {
    this.treasures = new ArrayList<>();
    this.coordinate = coordinate;
  }

  /**
   * Get the list of treasures this player has picked up.
   *
   * @return the list of treasures.
   */
  public List<Treasure> getTreasures() {
    List<Treasure> c = new ArrayList<>();
    c.addAll(this.treasures);
    return c;
  }

  /**
   * Add to the list of treasures this player has picked up.
   *
   * @param t The treasure picked up.
   */
  public void addTreasure(Treasure t) {
    treasures.add(t);
  }

  /**
   * Add to the list of treasures this player has picked up.
   *
   * @param t The treasure picked up.
   */
  public void addTreasure(List<Treasure> t) {
    treasures.addAll(t);
  }

  /**
   * Get the list of coordinate this player is placed.
   *
   * @return the coordinate.
   */
  public Coordinate getCoordinate() {
    return this.coordinate;
  }

  /**
   * Set the list of coordinate this player is placed.
   *
   * @param c The coordinate.
   */
  public void setCoordinate(Coordinate c) {
    this.coordinate = c;
  }
}

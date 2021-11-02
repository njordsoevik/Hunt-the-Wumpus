package dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * Player to access and navigate the dungeon. Players can also store treasure.
 */
class Player {
  private final List<Treasure> treasures;
  private Coordinate coordinate;

  /**
   * Constructor for a player.
   *
   * @param c The coordinate of the grid position for this player.
   */
  Player(Coordinate c) {
    this.treasures = new ArrayList<>();
    this.coordinate = c;
  }

  /**
   * Get the list of treasures this player has picked up.
   *
   * @return the list of treasures.
   */
  List<Treasure> getTreasures() {
    List<Treasure> c = new ArrayList<>();
    c.addAll(this.treasures);
    return c;
  }

  /**
   * Add to the list of treasures this player has picked up.
   *
   * @param t The treasure picked up.
   */
  void addTreasure(List<Treasure> t) {
    treasures.addAll(t);
  }

  /**
   * Get the list of coordinate this player is placed.
   *
   * @return the coordinate.
   */
  Coordinate getCoordinate() {
    return this.coordinate;
  }

  /**
   * Set the list of coordinate this player is placed.
   *
   * @param c The coordinate.
   */
  void setCoordinate(Coordinate c) {
    this.coordinate = c;
  }
}

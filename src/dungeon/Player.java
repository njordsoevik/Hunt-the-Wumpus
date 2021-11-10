package dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * Player to access and navigate the dungeon. Players can also store treasure.
 */
class Player {
  private List<Treasure> treasures;
  private int arrows;
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
   * Constructor for a player with arrows.
   *
   * @param c      The coordinate of the grid position for this player.
   * @param arrows The number of arrows for this player.
   */
  Player(Coordinate c, int arrows) {
    this.treasures = new ArrayList<>();
    this.coordinate = c;
    this.arrows = arrows;
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

  /**
   * Removes an arrow from the players inventory, used after an arrow is shot.
   */
  void removeArrow() {
    this.arrows = this.arrows - 1;
  }

  /**
   * Adds arrows to the players inventory.
   */
  void addArrow(int add) {
    this.arrows = this.arrows + add;
  }
}

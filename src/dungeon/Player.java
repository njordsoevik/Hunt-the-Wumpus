package dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * Player to access and navigate the dungeon. Players can also store treasure.
 */
class Player {
  private List<Treasure> treasures;
  private List<Arrow> arrows;
  private Coordinate coordinate;
  private Health health;

  /**
   * Constructor for a player.
   *
   * @param c The coordinate of the grid position for this player.
   */
  Player(Coordinate c) {
    this.treasures = new ArrayList<>();
    this.arrows = new ArrayList<>();
    this.coordinate = c;
    this.health = Health.HEALTHY;
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
    this.arrows.remove(0);
  }

  /**
   * Adds arrows to the players inventory.
   */
  void addArrow(List<Arrow> a) {
    this.arrows.addAll(a);
  }

  /**
   * Get the list of arrows this player has picked up.
   *
   * @return the list of arrows.
   */
  List<Arrow> getArrows() {
    List<Arrow> c = new ArrayList<>();
    c.addAll(this.arrows);
    return c;
  }

  /**
   * Get the health of this player.
   *
   * @return the player health.
   */
  public Health getHealth() {
    return this.health;
  }

  /**
   * Reduce the health of this player.
   *
   */
  public void reduceHealth() {
    this.health = Health.DEAD;
  }

}

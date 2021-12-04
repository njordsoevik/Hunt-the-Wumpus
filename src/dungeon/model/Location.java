package dungeon.model;

import java.util.Map;

/**
 * Locations throughout the dungeon are access points. These navigated by the player to move through
 * the dungeon.
 */
interface Location extends RLocation {

  /**
   * Add to the list of valid locations this location is connected to.
   *
   * @param l The location to place in this location's path list.
   */
  void addPath(Direction dir, Location l);

  /**
   * Add a treasure this location is holding.
   *
   * @param t The treasure to place in this location.
   */
  void addTreasure(Treasure t);

  /**
   * Remove the treasure this location is holding.
   */
  void removeTreasure();

  /**
   * Helper function for printing east edge of node.
   *
   * @return a line if the east edge exists.
   */
  String eastStringHelper();

  /**
   * Helper function for printing south edge of node.
   *
   * @return a line if the south edge exists.
   */
  String southStringHelper();


  /**
   * Add an Otyugh to this location.
   */
  void addOtyugh();

  /**
   * Add arrows to this location.
   */
  void addArrows(Arrow arrow);

  /**
   * Remove the arrows this location is holding.
   */
  void removeArrows();


  /**
   * Tag this location as visited by the player.
   */
  void setVisited();

  /**
   * Get a list of valid locations this location is connected to.
   *
   * @return the list of locations.
   */
  Map<Direction, Location> getPaths();


}

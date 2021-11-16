package dungeon.model;

import java.util.List;
import java.util.Map;

/**
 * Locations throughout the dungeon are access points. These navigated by the player to move through
 * the dungeon.
 */
interface Location {

  /**
   * Get a list of valid locations this location is connected to.
   *
   * @return the list of locations.
   */
  Map<Direction, Location> getPaths();

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
   * Get the treasure this location is holding.
   *
   * @return The treasure placed in this location.
   */
  List<Treasure> getTreasure();

  /**
   * Remove the treasure this location is holding.
   */
  void removeTreasure();

  /**
   * Get the coordinate of this location on the grid.
   *
   * @return the coordinate.
   */
  Coordinate getCoordinate();

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
   * Get the number of connections to this node, used to check if this is a tunnel or a cave.
   *
   * @return the location type of this node.
   */
  LocationType getLocationType();

  /**
   * Add an Otyugh to this location.
   */
  void addOtyugh();

  /**
   * Add arrows to this location.
   */
  void addArrows(Arrow arrow);

  /**
   * Get the arrows this location is holding.
   *
   * @return The arrows placed in this location.
   */
  List<Arrow> getArrows();

  /**
   * Remove the arrows this location is holding.
   */
  void removeArrows();

  /**
   * Return an Otyugh if one resides in this location.
   *
   * @return An Otyugh if one resides here, else null.
   */
  Otyugh getOtyugh();
}

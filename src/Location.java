import java.util.List;
import java.util.Map;

/**
 * Locations throughout the dungeon are access points. These navigated by the player to move through
 * the dungeon.
 */
public interface Location {

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
   *
   */
  void removeTreasure();

  /**
   * Get the coordinate of this location on the grid.
   *
   * @return the coordinate.
   */
  Coordinate getCoordinate();

}

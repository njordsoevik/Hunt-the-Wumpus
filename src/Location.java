import java.util.List;

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
  List<Location> getPaths();

  /**
   * Add to the list of valid locations this location is connected to.
   *
   * @param l The location to place in this location's path list.
   */
  void addPath(Location l);

  /**
   * Set the treasure this location is holding.
   *
   * @param t The treasure to place in this location.
   */
  void setTreasure(Treasure t);

  /**
   * Get the treasure this location is holding.
   *
   * @param t The treasure placed in this location.
   */
  Treasure getTreasure(Treasure t);

}

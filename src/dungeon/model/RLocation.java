package dungeon.model;

import java.util.List;
import java.util.Set;

/**
 * The read interface for locations. Read methods for locations return useful information
 * about items that are held within, and any useful effects.
 */
public interface RLocation {

  /**
   * Return if location has been visited by the player.
   *
   * @return True if a player has visited here, else false.
   */
  Boolean getVisited();

  /**
   * Get the number of connections to this node, used to check if this is a tunnel or a cave.
   *
   * @return the location type of this node.
   */
  LocationType getLocationType();

  /**
   * Get the coordinate of this location on the grid.
   *
   * @return the coordinate.
   */
  Coordinate getCoordinate();

  /**
   * Return an Otyugh's health if one resides in this location.
   *
   * @return An Otyugh's health if one resides here, else null.
   */
  Health getOtyughHealth();

  /**
   * Return if a thief resides in this location.
   *
   * @return True if a thief resides here, else False.
   */
  Thief getThief();

  /**
   * Get the treasure this location is holding.
   *
   * @return The treasure placed in this location.
   */
  List<Treasure> getTreasure();


  /**
   * Get a list of valid directions this location is connected to.
   *
   * @return the list of locations.
   */
  Set<Direction> getDirections();

  /**
   * Get the arrows this location is holding.
   *
   * @return The arrows placed in this location.
   */
  List<Arrow> getArrows();

}

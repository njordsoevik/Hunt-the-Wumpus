package dungeon.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
   * Return an Otyugh if one resides in this location.
   *
   * @return An Otyugh if one resides here, else null.
   */
  Otyugh getOtyugh();


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
}

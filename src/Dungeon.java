import java.util.List;
import java.util.Set;

/**
 * Dungeons consist of many locations which are access points for the player to navigate through.
 * Each location has a chance of containing treasure which can be picked up. There is a starting
 * location and ending location in the dungeon.
 */
public interface Dungeon {

  /**
   * Move a player to the valid desired location, NORTH, SOUTH, EAST, or WEST.
   *
   * @param direction The direction in the dungeon to move the player.
   * @throws IllegalArgumentException If the direction is not available to move.
   */
  void movePlayer(Direction direction);

  /**
   * Get the treasure placed at the current location.
   *
   * @return the treasure placed.
   */
  Treasure getCurrentLocationTreasure();

  /**
   * Get available directions for the player to move, NORTH, SOUTH, EAST, or WEST.
   *
   * @return set of directions.
   */
  Set<Direction> getDirections();

  /**
   * Get the treasures that the player is currently holding.
   *
   * @return the list of treasures.
   */
  List<Treasure> getPlayerTreasure();

  /**
   * Take the treasure placed at the current location.
   *
   * @throws IllegalArgumentException If no treasure exists.
   */
  void takeTreasure();

}

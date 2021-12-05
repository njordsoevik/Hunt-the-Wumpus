package dungeon.model;

import java.util.List;
import java.util.Set;

public interface RDungeon {

  /**
   * Get the arrows that the player is currently holding.
   *
   * @return the list of arrows.
   */
  List<Arrow> getPlayerArrows();

  /**
   * Get the arrows placed at the current location.
   *
   * @return the arrow in the player's location.
   */
  List<Arrow> getCurrentLocationArrows();

  /**
   * Get player health.
   *
   * @return the players health.
   */
  Health getPlayerHealth();

  /**
   * Get smell of nearby Otyughs. Detecting a more pungent smell either means that there is a
   * single Otyugh 1 position from the player's current location or that there are multiple
   * Otyughs within 2 positions from the player's current location.
   *
   * @return the smell of nearby Otyughs.
   */
  Smell getSmell();

  /**
   * Get the treasure placed at the current location.
   *
   * @return the treasure in the player's location.
   */
  List<Treasure> getCurrentLocationTreasure();

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
   * Check if the game is over by reaching the final square.
   */
  boolean isGameOver();

  /**
   * Get the list of visited locations, for mapping.
   *
   * @return the list of locations.
   */
  RLocation[][] getVisitedLocations();

  /**
   * Get coordinate of the current location.
   *
   * @return the coordinate of the current location.
   */
  Coordinate getCurrentCoordinate();
}
package dungeon.model;

import java.util.List;

/**
 * OtyughDungeon is similar to a regular dungeon, except the locations can hold arrows that the
 * player can shoot. In addition, dangerous Otyughs exist within the caves that kill players.
 * Players can shoot and kill these Otyughs.
 */
public interface OtyughDungeon extends Dungeon, RDungeon {

  /**
   * Pick up arrows in the current location.
   */
  void takeArrows();

  /**
   * Shoot an arrow in a specified distance and direction. This hits an Otyugh only if the distance
   * is exact to the Otyugh's location. Two hits are required to kill an Otyugh. The arrow will
   * "curve" through a tunnel location
   *
   * @param dir      The direction to shoot the arrow.
   * @param distance The distance to shoot the arrow.
   */
  void shootArrow(Direction dir, int distance);

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

}
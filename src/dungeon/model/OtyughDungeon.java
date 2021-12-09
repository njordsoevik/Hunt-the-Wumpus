package dungeon.model;


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
   * @param dir The direction to shoot the arrow.
   * @param distance The distance to shoot the arrow.
   * @throws IllegalArgumentException If the direction or distance is invalid.
   */
  void shootArrow(Direction dir, int distance);

}
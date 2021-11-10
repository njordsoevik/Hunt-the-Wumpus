package dungeon;

public interface OtyughDungeon extends Dungeon {

  /**
   * Pick up arrows in the current location.
   *
   */
  void pickUpArrows();

  /**
   * Shoot an arrow in a specified distance and direction. This hits an Otyugh only if the distance
   * is exact to the Otyugh's location. Two hits are required to kill an Otyugh.
   *
   * @param dir The direction to shoot the arrow.
   * @param distance The distance to shoot the arrow.
   *
   */
  void shootArrow(Direction dir, int distance);

}

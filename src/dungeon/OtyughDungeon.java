package dungeon;

import java.util.List;

public interface OtyughDungeon extends Dungeon {

  /**
   * Pick up arrows in the current location.
   *
   */
  void takeArrows();

  /**
   * Shoot an arrow in a specified distance and direction. This hits an Otyugh only if the distance
   * is exact to the Otyugh's location. Two hits are required to kill an Otyugh.
   *
   * @param dir The direction to shoot the arrow.
   * @param distance The distance to shoot the arrow.
   *
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

}

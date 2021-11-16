package dungeon.model;

/**
 * Directions that can be used to access each location. These are the basic directions of North,
 * South, East, and West.
 */
public enum Direction {
  NORTH,
  SOUTH,
  EAST,
  WEST;

  /**
   * Return the opposite direction of this direction, NORTH, SOUTH, EAST, or WEST.
   *
   * @return Direction The opposite direction.
   */
  public Direction getInverse() {
    if (this == NORTH) {
      return SOUTH;
    } else if (this == SOUTH) {
      return NORTH;
    } else if (this == EAST) {
      return WEST;
    } else {
      return EAST;
    }
  }

}

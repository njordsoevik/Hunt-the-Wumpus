
/**
 * Directions that can be used to access each location. These are the basic directions of North,
 * South, East, and West.
 */
public enum Direction {
  NORTH(0,1),
  SOUTH(0,-1),
  EAST(1,0),
  WEST(-1,0);

  private final int x;
  private final int y;

  Direction(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.x;
  }
}

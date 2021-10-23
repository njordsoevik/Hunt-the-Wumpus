
/**
 * Directions that can be used to access each location. These are the basic directions of North,
 * South, East, and West.
 */
public enum Direction {
  NORTH(new Coordinate(1,0)),
  SOUTH(new Coordinate(-1,0)),
  EAST(new Coordinate(0,1)),
  WEST(new Coordinate(0,-1));

  private final Coordinate c;

  Direction(Coordinate c) {
    this.c=c;
  }

  public Coordinate getCoordinate() {
    return this.c;
  }

  public Direction getInverse() {
    if (this == NORTH) {
      return SOUTH;
    }
    else if (this == SOUTH) {
      return NORTH;
    }
    else if (this == EAST) {
      return WEST;
    }
    else {
      return EAST;
    }
  }

}

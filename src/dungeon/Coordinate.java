package dungeon;

import java.util.Objects;

/**
 * Coordinates keep track of the physical location of each cave, tunnel and player.
 */
class Coordinate {
  int i;
  int j;

  public Coordinate(int i, int j) {
    this.i = i;
    this.j = j;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Coordinate)) {
      return false;
    }
    Coordinate c = (Coordinate) o;
    return c.i == i && c.j == j;
  }

  public int getI() {
    return this.i;
  }

  public int getJ() {
    return this.j;
  }

  @Override
  public int hashCode() {
    return Objects.hash(i, j);
  }

  @Override
  public String toString() {
    return String.format("[" + this.i + "," + "" + this.j + "]");
  }
}

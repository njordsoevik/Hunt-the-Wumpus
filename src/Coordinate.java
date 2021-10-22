import java.util.Objects;

class Coordinate {
  int x;
  int y;

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Coordinate)) {
      return false;
    }
    Coordinate c = (Coordinate) o;
    return c.x == x && c.y == y;
  }

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int distanceTo(Coordinate c) {
    double xDistance = Math.pow((this.getX() - c.getX()), 2);
    double yDistance = Math.pow((this.getY() - c.getY()), 2);
    double result = Math.pow(xDistance+yDistance,1/2);
    return (int) result;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    return String.format("[" + this.x + "," + "" + this.y + "]");
  }
}

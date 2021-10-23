import java.util.Objects;

public class Coordinate {
  int i;
  int j;

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Coordinate)) {
      return false;
    }
    Coordinate c = (Coordinate) o;
    return c.i == i && c.j == j;
  }

  public Coordinate(int i, int j) {
    this.i = i;
    this.j = j;
  }

  public int getI() {
    return this.i;
  }

  public int getJ() {
    return this.j;
  }

  public Coordinate addCoordinate (Coordinate c) {
    return new Coordinate(this.getI()+c.getJ(), this.getI()+c.getJ());
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

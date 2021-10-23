import java.util.List;

/**
 * Edges connect each dungeon location adjacent locations. These edges also contain a weight,
 * used to skew the results of dungeon generation.
 */
public class Edge implements Comparable<Edge>{
  private int weight;
  private Direction direction;
  private Coordinate source;
  private Coordinate destination;

  public Edge(int weight, Coordinate source, Coordinate destination, Direction direction) {
    this.direction = direction;
    this.weight = weight;
    this.source = source;
    this.destination = destination;
  }

  public int getWeight(){
    return this.weight;
  }

  public Direction getDirection(){
    return this.direction;
  }

  public Coordinate getSource() {
    return this.source;
  }

  public Coordinate getDestination() {
    return this.destination;
  }

  @Override
  public int compareTo(Edge o) {
    return this.weight - o.weight;
  }

  @Override
  public String toString() {
    return String.format("Source:" +this.source+", " +"Destination: "+this.destination);
  }
}
package dungeon;

/**
 * Edges connect each dungeon location adjacent locations. These edges also contain a weight,
 * used to skew the results of dungeon generation.
 */
class Edge implements Comparable<Edge>{
  private int weight;
  private Direction direction;
  private Coordinate source;
  private Coordinate destination;

  /**
   * Constructor for an edge.
   *
   * @param direction The direction this edge connections source and destination locations.
   * @param source The source location of this edge.
   * @param destination The destination location of this edge.
   * @param weight The weight of the edge for dungeon generation.
   */
  public Edge(int weight, Coordinate source, Coordinate destination, Direction direction) {
    this.direction = direction;
    this.weight = weight;
    this.source = source;
    this.destination = destination;
  }

  Direction getDirection(){
    return this.direction;
  }

  Coordinate getSource() {
    return this.source;
  }

  Coordinate getDestination() {
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
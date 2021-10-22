import java.util.List;

/**
 * Edges connect each dungeon location adjacent locations. These edges also contain a weight,
 * used to skew the results of dungeon generation.
 */
public class Edge implements Comparable<Edge>{
  private int weight;
  private List<Location> locations;

  public Edge(int weight, List<Location> locations) {
    this.weight = weight;
    this.locations = locations;
  }

  public int getWeight(){
    return this.weight;
  }

  public List<Location> getLocations() {
    return this.locations;
  }

  @Override
  public int compareTo(Edge o) {
    return this.weight - o.weight;
  }
}

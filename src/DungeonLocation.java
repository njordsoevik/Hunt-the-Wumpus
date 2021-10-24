import java.util.*;

/**
 * DungeonLocation implements the Location interface. These navigated by the player to move through
 * the dungeon. Dungeons can also store treasure.
 */
public class DungeonLocation implements Location {
  private List<Treasure> treasure;
  private HashMap<Direction, Location> connections;
  private Coordinate coordinate;

  public DungeonLocation(Coordinate c) {
    this.connections = new HashMap<>();
    this.treasure = new ArrayList<>();
    this.coordinate = c;
  }

  @Override
  public Map<Direction, Location> getPaths() {
    Map<Direction, Location> copy = new HashMap<>();
    copy.putAll(connections);
    return copy;
  }

  @Override
  public void addPath(Direction dir, Location l) {
    connections.put(dir, l);
  }

  @Override
  public void addTreasure(Treasure t) {
    this.treasure.add(t);
  }

  @Override
  public List<Treasure> getTreasure() {
    return this.treasure;
  }

  @Override
  public void removeTreasure() {
    this.treasure.clear();
  }

  @Override
  public Coordinate getCoordinate() {
    return new Coordinate(coordinate.getI(), coordinate.getJ());
  }

  @Override
  public String toString() {
    Set<Direction> d = connections.keySet();
    String n = stringHelper(d, Direction.NORTH);
    String s = stringHelper(d, Direction.SOUTH);
    String e = stringHelper(d, Direction.EAST);
    String w = stringHelper(d, Direction.WEST);
    return String.format(" %s \n%s0%s\n %s ", n,w,e,s);
  }

  private String stringHelper(Set<Direction> s, Direction dir) {
    if (s.contains(dir)) {
      if (dir == Direction.NORTH || dir == Direction.SOUTH) {
        return "|";
      } else if (!(dir == null)) {
        return "-";
      }
    }
    return " ";
  }

  public static void main(String[] args) {
    Location p = new DungeonLocation(new Coordinate(1,1));
    p.addPath(Direction.NORTH,p);
    p.addPath(Direction.SOUTH,p);
    p.addPath(Direction.EAST,p);
    System.out.println(p.toString());
  }

}

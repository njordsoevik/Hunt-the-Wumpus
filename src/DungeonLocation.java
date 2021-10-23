import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DungeonLocation implements the Location interface. These navigated by the player to move through
 * the dungeon. Dungeons can also store treasure.
 */
public class DungeonLocation implements Location {
  private Treasure treasure;
  private HashMap<Direction,Location> connections;
  private Coordinate coordinate;

  public DungeonLocation (Coordinate c) {
    this.connections = new HashMap<>();
    this.treasure = null;
    this.coordinate = c;
  }

  @Override
  public Map<Direction,Location> getPaths() {
    return connections;
  }

  @Override
  public void addPath(Direction dir, Location l) {
    connections.put(dir,l);
  }

  @Override
  public void setTreasure(Treasure t) {
    this.treasure = t;
  }

  @Override
  public Treasure getTreasure(Treasure t) {
    return this.treasure;
  }

  @Override
  public Coordinate getCoordinate() {
    return new Coordinate(coordinate.getI(),coordinate.getJ());
  }

}

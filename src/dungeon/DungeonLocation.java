package dungeon;

import java.util.*;

/**
 * DungeonLocation implements the dungeon.Location interface. These navigated by the player to move through
 * the dungeon. Dungeons can also store treasure.
 */
public class DungeonLocation implements Location {
  private List<Treasure> treasure;
  private HashMap<Direction, Location> connections;
  private Coordinate coordinate;

  /**
   * Constructor for a dungeon location.
   *
   * @param c The coordinate of the grid position for this location.
   */
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
    return "0";
  }

  @Override
  public String southStringHelper() {
    if (connections.keySet().contains(Direction.SOUTH)) {
      return "|";
    }
    return " ";
  }

  @Override
  public LocationType getLocationType() {
    if (this.connections.size() == 2) {
      return LocationType.TUNNEL;
    } else {
      return LocationType.CAVE;
    }
  }

  @Override
  public String eastStringHelper() {
    if (connections.keySet().contains(Direction.EAST)) {
      return "-";
    }
    return " ";
  }
}

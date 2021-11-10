package dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DungeonLocation implements the dungeon.Location interface. These navigated by the player to
 * move through the dungeon. Dungeons can also store treasure.
 */
class DungeonLocation implements Location {
  private List<Treasure> treasure;
  private List<Arrow> arrows;
  private HashMap<Direction, Location> connections;
  private final Coordinate coordinate;
  private boolean containsOtyugh;

  /**
   * Constructor for a dungeon location.
   *
   * @param c The coordinate of the grid position for this location.
   */
  public DungeonLocation(Coordinate c) {
    this.connections = new HashMap<>();
    this.treasure = new ArrayList<>();
    this.coordinate = c;
    this.containsOtyugh = false;
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
    List<Treasure> copy = new ArrayList<>();
    copy.addAll(this.treasure);
    return copy;
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
    if (connections.containsKey(Direction.SOUTH)) {
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
  public void addOtyugh() {
    if (this.containsOtyugh) {
      throw new IllegalStateException("Cannot contain two Otyughs");
    }
    this.containsOtyugh = true;
  }

  @Override
  public void addArrows(Arrow arrow) {
    this.arrows.add(arrow);
  }

  @Override
  public List<Arrow> getArrows() {
    List<Arrow> copy = new ArrayList<>();
    copy.addAll(this.arrows);
    return copy;
  }

  @Override
  public void removeArrows() {
    this.arrows.clear();
  }

  @Override
  public String eastStringHelper() {
    if (connections.containsKey(Direction.EAST)) {
      return "-";
    }
    return " ";
  }
}

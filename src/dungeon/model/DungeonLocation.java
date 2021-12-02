package dungeon.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DungeonLocation implements the dungeon.model.Location interface. These navigated by the player to
 * move through the dungeon. Dungeons can also store treasure.
 */
public final class DungeonLocation implements Location {
  private final Coordinate coordinate;
  private final List<Treasure> treasure;
  private final List<Arrow> arrows;
  private final Map<Direction, Location> connections;
  private Otyugh otyugh;
  private boolean visited;

  /**
   * Constructor for a dungeon location.
   *
   * @param c The coordinate of the grid position for this location.
   */
  public DungeonLocation(Coordinate c) {
    this.connections = new HashMap<>();
    this.treasure = new ArrayList<>();
    this.arrows = new ArrayList<>();
    this.coordinate = c;
    this.visited = false;
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
    if (this.otyugh == null) {
      return "0";
    } else if (this.otyugh.getHealth() == Health.HEALTHY) {
      return "H";
    } else if (this.otyugh.getHealth() == Health.INJURED) {
      return "I";
    } else {
      return "D";
    }
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
    if (this.otyugh != null) {
      throw new IllegalStateException("Cannot contain two Otyughs");
    }
    this.otyugh = new Otyugh();
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
  public Otyugh getOtyugh() {
    return this.otyugh;
  }

  @Override
  public void setVisited() {
    this.visited = true;
  }

  @Override
  public Boolean getVisited() {
    return this.visited;
  }

  @Override
  public String eastStringHelper() {
    if (connections.containsKey(Direction.EAST)) {
      return "-";
    }
    return " ";
  }
}

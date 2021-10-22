import java.util.ArrayList;
import java.util.List;

/**
 * DungeonLocation implements the Location interface. These navigated by the player to move through
 * the dungeon. Dungeons can also store treasure.
 */
public class DungeonLocation implements Location{
  private Treasure treasure;
  private List<Location> connections;

  public DungeonLocation() {
    this.connections = new ArrayList<>();
    this.treasure = null;
  }

  @Override
  public List<Location> getPaths() {
    return connections;
  }

  @Override
  public void addPath(Location l) {
    connections.add(l);
  }

  @Override
  public void setTreasure(Treasure t) {
    this.treasure = t;
  }

  @Override
  public Treasure getTreasure(Treasure t) {
    return this.treasure;
  }

}

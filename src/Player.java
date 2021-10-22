import java.util.ArrayList;
import java.util.List;

/**
 * Player to access and navigate the dungeon. Players can also store treasure.
 */
class Player {
  private List<Treasure> treasures;

  /**
   * Get the list of treasures this player has picked up.
   *
   * @return the list of treasures.
   */
  public List<Treasure> getTreasures() {
    List<Treasure> c = new ArrayList<>();
    c.addAll(this.treasures);
    return c;
  }
}

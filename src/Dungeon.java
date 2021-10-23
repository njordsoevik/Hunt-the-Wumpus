import java.util.List;
import java.util.Set;

public interface Dungeon {
  void movePlayer(Direction dir);
  Treasure getCurrentLocationTreasure();
  Set<Direction> getDirections();
  List<Treasure> getPlayerTreasure();
  void takeTreasure();

}

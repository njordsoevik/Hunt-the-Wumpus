import java.util.List;
import java.util.Map;

public interface Dungeon {
  void movePlayer(Direction dir);
  List<Direction> getDirections();
  List<Treasure> getPlayerTreasure();
  void takeTreasure();

}

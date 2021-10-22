import java.util.List;

public interface Dungeon {
  void movePlayer(Direction dir);
  Treasure getCurrentLocationTreasure();
  List<Direction> getDirections();
  List<Treasure> getPlayerTreasure();
  void takeTreasure();

}

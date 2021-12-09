import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dungeon.model.Arrow;
import dungeon.model.Coordinate;
import dungeon.model.Direction;
import dungeon.model.Health;
import dungeon.model.OtyughDungeon;
import dungeon.model.RDungeon;
import dungeon.model.RLocation;
import dungeon.model.Smell;
import dungeon.model.Treasure;

/**
 * A mock to simulate a model to check controller input to model.
 */
public class MockModel implements OtyughDungeon, RDungeon {
  public String inputOne;
  public String inputTwo;
  public Set<Direction> d;

  /**
   * A mock model constructor to give all directions as a possible move.
   */
  public MockModel() {
    d = new HashSet<>();
    d.add(Direction.NORTH);
    d.add(Direction.WEST);
    d.add(Direction.EAST);
    d.add(Direction.SOUTH);
  }

  public void movePlayer(Direction direction) {
    inputOne = direction.toString();
    return;
  }

  public List<Treasure> getCurrentLocationTreasure() {
    List<Treasure> t = new ArrayList<>();
    return t;
  }

  @Override
  public Set<Direction> getDirections() {
    return d;
  }

  @Override
  public List<Treasure> getPlayerTreasure() {
    List<Treasure> t = new ArrayList<>();
    return t;
  }

  @Override
  public void takeTreasure() {
    return;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public RLocation[][] getVisitedLocations() {
    return new RLocation[0][];
  }

  @Override
  public Coordinate getCurrentCoordinate() {
    return null;
  }

  @Override
  public void takeArrows() {
    return;
  }

  @Override
  public void shootArrow(Direction dir, int distance) {
    inputOne = dir.toString();
    inputTwo = String.valueOf(distance);
  }

  @Override
  public List<Arrow> getPlayerArrows() {
    List<Arrow> t = new ArrayList<>();
    return t;
  }

  @Override
  public List<Arrow> getCurrentLocationArrows() {
    List<Arrow> t = new ArrayList<>();
    return t;
  }

  @Override
  public Health getPlayerHealth() {
    return Health.HEALTHY;
  }

  @Override
  public Smell getSmell() {
    return Smell.NONE;
  }
}

import org.junit.Before;
import org.junit.Test;

import java.util.Random;


public class DungeonTest {
  Dungeon p;
  Dungeon q;

  @Before
  public void setUp() throws Exception {
    p = new TreasureDungeon(4,5,0,true);
    q = new TreasureDungeon(4,5,0,false);
  }

  @Test
  public void movePlayer() {

  }

  @Test
  public void getCurrentLocationTreasure() {
  }

  @Test
  public void getDirections() {
    System.out.println(q.getDirections());
    q.movePlayer(Direction.NORTH);
    System.out.println(q.getDirections());
  }

  @Test
  public void getPlayerTreasure() {
  }

  @Test
  public void takeTreasure() {
  }
}
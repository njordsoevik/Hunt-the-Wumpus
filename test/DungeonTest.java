import org.junit.Before;
import org.junit.Test;

import java.util.Random;


public class DungeonTest {

  @Before
  public void setUp() throws Exception {
    Dungeon p = new TreasureDungeon(4,5,0,true);
    Dungeon q = new TreasureDungeon(4,5,0,false);
  }

  @Test
  public void movePlayer() {

  }

  @Test
  public void getCurrentLocationTreasure() {
  }

  @Test
  public void getDirections() {
  }

  @Test
  public void getPlayerTreasure() {
  }

  @Test
  public void takeTreasure() {
  }
}
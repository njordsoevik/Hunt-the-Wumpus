import org.junit.Before;
import org.junit.Test;

import java.util.Random;


public class DungeonTest {
  Dungeon p;
  Dungeon q;

  @Before
  public void setUp(){
    p = new TreasureDungeon(14,15,0,true,
            20,1L);
    q = new TreasureDungeon(14,15,0,false,
            100,2L);
  }

  @Test (expected = IllegalArgumentException.class)
  public void createInvalidDungeonRows() {
    p = new TreasureDungeon(-14,15,2,true, 20);
  }
  @Test (expected = IllegalArgumentException.class)
  public void createInvalidDungeonColumns() {
    p = new TreasureDungeon(14,-15,3,true, 20);
  }
  @Test (expected = IllegalArgumentException.class)
  public void createInvalidDungeonInterconnectivity() {
    p = new TreasureDungeon(14,15,-3,true, 20);
  }
  @Test (expected = IllegalArgumentException.class)
  public void createInvalidDungeonTreasurePercent() {
    p = new TreasureDungeon(14,15,1,true, -20);
  }
  @Test (expected = IllegalArgumentException.class)
  public void createInvalidDungeonNotEnoughDistance() {
    p = new TreasureDungeon(1,2,1,true, 120);
  }
  @Test
  public void createValidDungeonWrapped() {
    p = new TreasureDungeon(11,22,1,true, 120);
  }
  @Test
  public void createValidDungeonOver100Treasure() {
    p = new TreasureDungeon(11,22,1,true, 120);
  }
  @Test
  public void createValidDungeonUnWrapped() {
    p = new TreasureDungeon(11,22,1,false, 120);
  }


  @Test
  public void getCurrentLocationTreasure() {
    System.out.println(q.getDirections());
    System.out.println(q.getCurrentLocationTreasure());
    q.movePlayer(Direction.WEST);
    System.out.println(q.getDirections());
    System.out.println(q.getCurrentLocationTreasure());
    q.movePlayer(Direction.SOUTH);
    System.out.println(q.getCurrentLocationTreasure());
  }

  @Test
  public void getDirections() {
    System.out.println(q.getDirections());

    q.getDirections();
  }

  @Test
  public void movePlayerWrapped() {


  }


  @Test
  public void movePlayerUnwrapped() {

  }


  @Test
  public void getPlayerTreasure() {
  }

  @Test
  public void takeTreasure() {
  }
}
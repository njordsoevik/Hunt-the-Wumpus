import org.junit.Before;
import org.junit.Test;

import java.util.Random;


public class DungeonTest {
  Dungeon p;
  Dungeon q;

  @Before
  public void setUp() throws Exception {
    p = new TreasureDungeon(4,5,0,true,1L);
    q = new TreasureDungeon(4,5,0,false, 2L);
  }


  @Test
  public void getCurrentLocationTreasure() {
  }

  @Test
  public void getDirections() {
    System.out.println(q.getDirections());
    q.getDirections();
  }

  @Test
  public void movePlayerWrapped() {
    System.out.println(p.getDirections());
    p.movePlayer(Direction.SOUTH);
    System.out.println(p.getDirections());
    p.movePlayer(Direction.EAST);
    System.out.println(p.getDirections());
    p.movePlayer(Direction.SOUTH);
    System.out.println(p.getDirections());

  }


  @Test
  public void movePlayerUnwrapped() {
    System.out.println(q.getDirections()); // Start east wall
    q.movePlayer(Direction.SOUTH);
    System.out.println(q.getDirections());
    q.movePlayer(Direction.WEST);
    System.out.println(q.getDirections());
    q.movePlayer(Direction.EAST); // backtracking
    System.out.println(q.getDirections());
    q.movePlayer(Direction.WEST);
    System.out.println(q.getDirections());
    q.movePlayer(Direction.WEST);
    System.out.println(q.getDirections());
    q.movePlayer(Direction.WEST);
    System.out.println(q.getDirections());
    q.movePlayer(Direction.WEST);
    System.out.println(q.getDirections()); // End west wall
  }


  @Test
  public void getPlayerTreasure() {
  }

  @Test
  public void takeTreasure() {
  }
}
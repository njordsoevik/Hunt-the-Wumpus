import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import dungeon.Arrow;
import dungeon.Direction;
import dungeon.controller.DungeonConsoleController;
import dungeon.controller.DungeonController;
import dungeon.OtyughDungeon;
import dungeon.OtyughTreasureDungeon;

public class OtyughDungeonTest {
  OtyughDungeon p;
  OtyughDungeon q;

  @Before
  public void setUp() {
  }

  @Test(expected = IllegalArgumentException.class)
  public void addOneTooManyOtyughs() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 10, 8,5L);
  }

  @Test
  public void placeExactAmountOtyughs() {
    new OtyughTreasureDungeon(3, 4, 0, false, 20, 10, 7, 5L);
  }

  @Test
  public void checkStartingArrows() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 100, 7, 5L);
    List<Arrow> a = new ArrayList<>();
    a.add(Arrow.CROOKED);
    a.add(Arrow.CROOKED);
    a.add(Arrow.CROOKED);
    Assert.assertEquals(z.getPlayerArrows(),a);
  }

  @Test
  public void checkLocationArrowsOne() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 100, 7, 5L);
    List<Arrow> a = new ArrayList<>();
    a.add(Arrow.CROOKED);
    Assert.assertEquals(z.getCurrentLocationArrows(),a);
  }

  @Test
  public void checkLocationArrowsTwo() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 200, 7, 5L);
    List<Arrow> a = new ArrayList<>();
    a.add(Arrow.CROOKED);
    a.add(Arrow.CROOKED);
    Assert.assertEquals(z.getCurrentLocationArrows(),a);
  }

  @Test
  public void checkOtyughDies() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 200, 7, 5L);
    System.out.println(z);
    z.shootArrow(Direction.SOUTH, 1);
    System.out.println(z);
    z.shootArrow(Direction.SOUTH, 1);
    System.out.println(z);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.SOUTH);
    z.shootArrow(Direction.SOUTH, 1);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.NORTH); //repeat this to die

  }
  @Test
  public void testShooting() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 1000, 7, 5L);
    z.takeArrows();
    z.shootArrow(Direction.SOUTH, 1);
    z.shootArrow(Direction.SOUTH, 1);
    z.shootArrow(Direction.SOUTH, 2);
    z.shootArrow(Direction.SOUTH, 2);
    z.movePlayer(Direction.SOUTH);
    z.shootArrow(Direction.EAST,1);
    z.shootArrow(Direction.EAST,1);
    z.movePlayer(Direction.EAST);
    System.out.println(z);
    z.shootArrow(Direction.NORTH,2);
    z.shootArrow(Direction.NORTH,2);
    z.shootArrow(Direction.NORTH,3);
    z.shootArrow(Direction.SOUTH,3);
    System.out.println(z);
  }
  @Test
  public void testReduceArrows() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 1000, 7, 5L);
    z.shootArrow(Direction.SOUTH, 1);
    System.out.println(z.getPlayerArrows());
    z.shootArrow(Direction.SOUTH, 1);
    z.shootArrow(Direction.SOUTH, 2);
    System.out.println(z.getPlayerArrows());
  }


  @Test
  public void controller() {
    Readable testInput = new StringReader("m south p s south 5");
    Appendable outputLog = new StringBuilder();
    Appendable testLog = new StringBuilder();
    OtyughDungeon m = new OtyughTreasureDungeon(3, 4, 0, false, 120, 50, 2, 5L);
    DungeonController c = new DungeonConsoleController(testInput,outputLog);
    System.out.println(m);
    c.playGame(m);

    System.out.println(outputLog);
  }
}

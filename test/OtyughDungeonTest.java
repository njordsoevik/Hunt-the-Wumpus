import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import dungeon.Arrow;
import dungeon.Direction;
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
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 10, 9,5L);
  }

  @Test
  public void placeExactAmountOtyughs() {
    new OtyughTreasureDungeon(3, 4, 0, false, 20, 10, 8, 5L);
  }

  @Test
  public void checkStartingArrows() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 100, 8, 5L);
    List<Arrow> a = new ArrayList<>();
    a.add(Arrow.CROOKED);
    a.add(Arrow.CROOKED);
    a.add(Arrow.CROOKED);
    Assert.assertEquals(z.getPlayerArrows(),a);
  }

  @Test
  public void checkLocationArrowsOne() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 100, 8, 5L);
    List<Arrow> a = new ArrayList<>();
    a.add(Arrow.CROOKED);
    Assert.assertEquals(z.getCurrentLocationArrows(),a);
  }

  @Test
  public void checkLocationArrowsTwo() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 200, 8, 5L);
    List<Arrow> a = new ArrayList<>();
    a.add(Arrow.CROOKED);
    a.add(Arrow.CROOKED);
    Assert.assertEquals(z.getCurrentLocationArrows(),a);
  }

  @Test
  public void check() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 200, 8, 5L);
    System.out.println(z.getDirections());
    z.shootArrow(Direction.SOUTH, 1);
    //z.shootArrow(Direction.SOUTH, 1);
    z.movePlayer(Direction.SOUTH);
    System.out.println(z.getDirections());
  }


}

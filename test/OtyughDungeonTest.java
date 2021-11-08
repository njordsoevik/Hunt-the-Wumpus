import org.junit.Before;
import org.junit.Test;

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
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 9, 5L);
  }

  @Test
  public void PlaceExactAmountOtyughs() {
    new OtyughTreasureDungeon(3, 4, 0, false, 20, 8, 5L);
  }

}

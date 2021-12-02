import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import dungeon.model.Direction;
import dungeon.model.Dungeon;
import dungeon.model.Treasure;
import dungeon.model.TreasureDungeon;

/**
 * A testing class for the dungeon interface.
 */
public class DungeonTest {
  Dungeon p;
  Dungeon q;

  @Before
  public void setUp() {
    p = new TreasureDungeon(14, 15, 0, true,
            20, 1L);
    q = new TreasureDungeon(14, 15, 0, false,
            100, 2L);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createInvalidDungeonRows() {
    p = new TreasureDungeon(-14, 15, 2, true, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createInvalidDungeonColumns() {
    p = new TreasureDungeon(14, -15, 3, true, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createInvalidDungeonInterconnectivity() {
    p = new TreasureDungeon(14, 15, -3, true, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createInvalidDungeonTreasurePercent() {
    p = new TreasureDungeon(14, 15, 1, true, -20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createInvalidDungeonNotEnoughDistance() {
    p = new TreasureDungeon(1, 2, 1, true, 120);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createInvalidDungeonMinimumSize() {
    p = new TreasureDungeon(3, 3, 1, true, 120);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createInvalidDungeonZeroRows() {
    p = new TreasureDungeon(0, 4, 1, true, 120);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createInvalidDungeonZeroColumns() {
    p = new TreasureDungeon(4, 0, 1, true, 120);
  }

  @Test
  public void getDirectionsWrapped() {
    Set<Direction> d = new HashSet<>();
    d.add(Direction.NORTH);
    d.add(Direction.WEST);
    Assert.assertEquals(d, q.getDirections());
  }

  @Test
  public void getDirectionsNonWrapped() {
    Set<Direction> d = new HashSet<>();
    d.add(Direction.EAST);
    Assert.assertEquals(d, p.getDirections());
  }

  @Test
  public void testMinimumPathDistanceMinimumSize() {
    for (int i = 0; i < 50; i++) {
      Dungeon z = new TreasureDungeon(3, 4, 0, false, 120, 4L);
      Random rand = new Random();
      int numberOfMoves = 0;
      while (!z.isGameOver()) {
        Set<Direction> ds = z.getDirections();
        Direction dir = null;
        int directionIndex = rand.nextInt(ds.size());
        int item = 0;
        for (Direction iterator : ds) {
          if (item == directionIndex) {
            dir = iterator;
            break;
          }
          item++;
        }
        numberOfMoves++;
        z.movePlayer(dir);
      }
      Assert.assertTrue(numberOfMoves >= 5); //Check at-least 5 moves were made in each run
      Assert.assertTrue(z.isGameOver());
    }
  }

  @Test
  public void movePlayerMaxInterconnectivityUnWrappedAllSquares() {
    Dungeon z = new TreasureDungeon(3, 4, 100, false, 120, 4L);
    // Move through all 9 squares, one path
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.WEST);
    Assert.assertTrue(z.isGameOver());
    try {
      z.movePlayer(Direction.EAST);
    } catch (IllegalArgumentException e) {
      System.out.println("Expected error:" + e);
    }
  }

  @Test
  public void movePlayerZeroInterconnectivityUnWrappedAllSquares() {
    Dungeon z = new TreasureDungeon(3, 4, 0, false, 120, 4L);
    // Move through all squares, one path
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.SOUTH);
    Assert.assertTrue(z.isGameOver());
    try {
      z.movePlayer(Direction.NORTH);
    } catch (IllegalArgumentException e) {
      System.out.println(e + " expected.");
    }
  }

  @Test
  public void movePlayerZeroInterconnectivityWrappedAllSquares() {
    Dungeon z = new TreasureDungeon(3, 4, 0, true, 120, 4L);
    // Move through all squares, one path
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.NORTH);
    Assert.assertTrue(z.isGameOver());
    try {
      z.movePlayer(Direction.EAST);
    } catch (IllegalArgumentException e) {
      System.out.println(e + " expected.");
    }
  }

  @Test
  public void checkPlusOneInterconnectivityAddsOnePath() {
    Dungeon z = new TreasureDungeon(3, 4, 0, false, 120, 4L);
    // Move through all squares, one path
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.SOUTH);
    Assert.assertTrue(z.isGameOver());

    Dungeon z2 = new TreasureDungeon(3, 4, 1, false, 120, 4L);
    // Move through all squares, one path
    z2.movePlayer(Direction.SOUTH);
    z2.movePlayer(Direction.SOUTH);
    z2.movePlayer(Direction.EAST);
    z2.movePlayer(Direction.EAST);
    z2.movePlayer(Direction.NORTH); // Going up new path
    z2.movePlayer(Direction.SOUTH); // Coming back on new path
    z2.movePlayer(Direction.WEST);
    z2.movePlayer(Direction.WEST);
    z2.movePlayer(Direction.NORTH);
    z2.movePlayer(Direction.EAST);
    z2.movePlayer(Direction.EAST);
    z2.movePlayer(Direction.WEST);
    z2.movePlayer(Direction.NORTH);
    z2.movePlayer(Direction.EAST);
    z2.movePlayer(Direction.EAST);
    z2.movePlayer(Direction.SOUTH);
    z2.movePlayer(Direction.SOUTH);
    Assert.assertTrue(z2.isGameOver());

  }

  @Test
  public void checkOverMaxInterconnectivityChangesNothing() {
    Dungeon z = new TreasureDungeon(3, 4, 50, false, 120, 4L);
    // Move through all 9 squares, one path
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.WEST);
    Assert.assertTrue(z.isGameOver());

    Dungeon z2 = new TreasureDungeon(3, 4, 100, false, 120, 4L);
    // Move through all 9 squares, one path
    z2.movePlayer(Direction.SOUTH);
    z2.movePlayer(Direction.SOUTH);
    z2.movePlayer(Direction.WEST);
    z2.movePlayer(Direction.NORTH);
    z2.movePlayer(Direction.NORTH);
    z2.movePlayer(Direction.WEST);
    z2.movePlayer(Direction.WEST);
    z2.movePlayer(Direction.SOUTH);
    z2.movePlayer(Direction.EAST);
    z2.movePlayer(Direction.SOUTH);
    z2.movePlayer(Direction.WEST);
    Assert.assertTrue(z2.isGameOver());
  }

  @Test
  public void movePlayerMaxInterconnectivityWrappedAllSquares() {
    Dungeon z = new TreasureDungeon(4, 8, 100, true, 120, 4L);

    for (int i = 0; i < 8; i++) {
      z.movePlayer(Direction.WEST);
      for (int j = 0; j < 4; j++) {
        if (i != 4) { // Skip the ending square, so we can test all squares
          z.movePlayer(Direction.NORTH);
        }
      }
    }
    for (int i = 0; i < 4; i++) {
      z.movePlayer(Direction.NORTH);
      for (int j = 0; j < 8; j++) {
        if ((i != 1 && j != 6)) { // Skip the ending square, so we can test all squares
          z.movePlayer(Direction.WEST);
        }
      }
    }
    for (int i = 0; i < 4; i++) {
      if (!z.isGameOver()) {
        z.movePlayer(Direction.NORTH);
      }
      for (int j = 0; j < 8; j++) {
        if (!z.isGameOver()) {
          z.movePlayer(Direction.WEST);
        }
      }
    }
    Assert.assertTrue(z.isGameOver());
  }

  @Test(expected = IllegalArgumentException.class)
  public void movePlayerUnWrappedEast() {
    Dungeon z = new TreasureDungeon(4, 8, 100, false, 120, 4L);
    for (int i = 0; i < 40; i++) {
      z.movePlayer(Direction.EAST);
    }
    for (int i = 0; i < 4; i++) {
      if (!z.isGameOver()) {
        z.movePlayer(Direction.NORTH);
      }
      for (int j = 0; j < 8; j++) {
        if (!z.isGameOver()) {
          z.movePlayer(Direction.WEST);
        }
      }
    }
    Assert.assertTrue(z.isGameOver());
  }

  @Test(expected = IllegalArgumentException.class)
  public void movePlayerNonWrappedWest() {
    Dungeon z = new TreasureDungeon(4, 8, 100, false, 120, 4L);
    for (int i = 0; i < 40; i++) {
      z.movePlayer(Direction.WEST);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void movePlayerNonWrappedNorth() {
    Dungeon z = new TreasureDungeon(4, 8, 100, false, 120, 4L);
    for (int i = 0; i < 40; i++) {
      z.movePlayer(Direction.NORTH);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void movePlayerNonWrappedSouth() {
    Dungeon z = new TreasureDungeon(4, 8, 100, false, 120, 4L);
    for (int i = 0; i < 40; i++) {
      z.movePlayer(Direction.SOUTH);
    }
  }

  @Test
  public void correctTreasurePercentZero() {
    Dungeon z = new TreasureDungeon(3, 4, 100, false, 0, 4L);
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    z.takeTreasure();
    z.movePlayer(Direction.NORTH);
    z.takeTreasure();
    z.movePlayer(Direction.NORTH);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    z.takeTreasure();
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.EAST);
    z.takeTreasure();
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    try {
      z.movePlayer(Direction.EAST);
    } catch (IllegalArgumentException e) {
      System.out.println(e + " expected.");
    }
    // Expect 1 less than 50% because of ending square holding a treasure
    Assert.assertEquals(0, z.getPlayerTreasure().size());
  }

  @Test
  public void correctTreasurePercent50() {
    Dungeon z = new TreasureDungeon(3, 4, 100, false, 50, 4L);
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    z.takeTreasure();
    z.movePlayer(Direction.NORTH);
    z.takeTreasure();
    z.movePlayer(Direction.NORTH);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    z.takeTreasure();
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.EAST);
    z.takeTreasure();
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    try {
      z.movePlayer(Direction.EAST);
    } catch (IllegalArgumentException e) {
      System.out.println(e + " expected.");
    }
    // Expect 1 less than 50% because of ending square holding a treasure
    Assert.assertEquals(5, z.getPlayerTreasure().size());
  }

  @Test
  public void correctTreasurePercent100() {
    Dungeon z = new TreasureDungeon(3, 4, 100, false, 100, 4L);
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    z.takeTreasure();
    z.movePlayer(Direction.NORTH);
    z.takeTreasure();
    z.movePlayer(Direction.NORTH);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    z.takeTreasure();
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.EAST);
    z.takeTreasure();
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    try {
      z.movePlayer(Direction.EAST);
    } catch (IllegalArgumentException e) {
      System.out.println(e + " expected.");
    }
    // Expect 12 - 4 = 8 because of tunnels
    Assert.assertEquals(8, z.getPlayerTreasure().size());
  }

  @Test
  public void correctTreasurePercentOver100() {
    Dungeon z = new TreasureDungeon(3, 4, 100, false, 200, 4L);
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    z.takeTreasure();
    z.movePlayer(Direction.NORTH);
    z.takeTreasure();
    z.movePlayer(Direction.NORTH);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    z.takeTreasure();
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.EAST);
    z.takeTreasure();
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    try {
      z.movePlayer(Direction.EAST);
    } catch (IllegalArgumentException e) {
      System.out.println(e + " expected.");
    }
    // Expect 12 - 4 = 8 because of tunnels
    Assert.assertEquals(16, z.getPlayerTreasure().size());
  }

  @Test
  public void correctTreasureTypes() {
    Dungeon z = new TreasureDungeon(3, 4, 100, false, 200, 4L);
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    z.takeTreasure();
    z.movePlayer(Direction.NORTH);
    z.takeTreasure();
    z.movePlayer(Direction.NORTH);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    z.takeTreasure();
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.EAST);
    z.takeTreasure();
    z.movePlayer(Direction.SOUTH);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    Assert.assertTrue(z.getPlayerTreasure().contains(Treasure.DIAMOND)
            && z.getPlayerTreasure().contains(Treasure.RUBY)
            && z.getPlayerTreasure().contains(Treasure.SAPPHIRE));
  }

  @Test
  public void getCurrentLocationNoTreasure() {
    Dungeon d = new TreasureDungeon(6, 7, 0, false, 0, 4L);
    List<Treasure> treasures = new ArrayList<>();
    Assert.assertEquals(treasures, d.getCurrentLocationTreasure());
  }

  @Test
  public void getCurrentLocationTreasureOne() {
    Dungeon d = new TreasureDungeon(6, 7, 0, false, 100, 4L);
    List<Treasure> treasures = new ArrayList<>();
    treasures.add(Treasure.SAPPHIRE);
    Assert.assertEquals(treasures, d.getCurrentLocationTreasure());
  }

  @Test
  public void getCurrentLocationTreasureMultiple() {
    Dungeon d = new TreasureDungeon(6, 7, 0, false, 300, 4L);
    List<Treasure> treasures = new ArrayList<>();
    treasures.add(Treasure.SAPPHIRE);
    treasures.add(Treasure.RUBY);
    treasures.add(Treasure.RUBY);
    Assert.assertEquals(treasures, d.getCurrentLocationTreasure());
  }

  @Test
  public void checkTunnelVsCaveTreasure() {
    Dungeon d = new TreasureDungeon(3, 4, 3, false, 300, 4L);
    Assert.assertEquals(1, d.getDirections().size()); // check cave size 1
    Assert.assertTrue(!d.getCurrentLocationTreasure().isEmpty()); // has treasure
    d.movePlayer(Direction.SOUTH);
    Assert.assertEquals(3, d.getDirections().size()); // check cave size 3
    Assert.assertTrue(!d.getCurrentLocationTreasure().isEmpty()); // has treasure
    d.movePlayer(Direction.EAST);
    Assert.assertEquals(4, d.getDirections().size());// check cave size 4
    Assert.assertTrue(!d.getCurrentLocationTreasure().isEmpty());// has treasure
    d.movePlayer(Direction.NORTH);
    Assert.assertEquals(2, d.getDirections().size());// check cave size 4
    Assert.assertTrue(d.getCurrentLocationTreasure().isEmpty());// has no treasure
  }


  @Test
  public void takeTreasureInEmptyCaveNone() {
    Dungeon k = new TreasureDungeon(14, 15, 2, true, 0, 4L);
    List<Treasure> player_treasures = new ArrayList<>();
    Assert.assertEquals(player_treasures, k.getPlayerTreasure()); // Check start no treasure
    List<Treasure> treasures = new ArrayList<>();
    Assert.assertEquals(treasures, k.getCurrentLocationTreasure());
    k.takeTreasure();
    Assert.assertEquals(player_treasures, k.getPlayerTreasure()); // Check no treasure added
    Assert.assertEquals(treasures, k.getCurrentLocationTreasure()); // Check no treasure removed
  }

  @Test
  public void takeAndGetTreasureOne() {
    Dungeon q = new TreasureDungeon(4, 6, 100, true, 100, 4L);
    List<Treasure> player_treasures = new ArrayList<>();
    Assert.assertEquals(player_treasures, q.getPlayerTreasure()); // Check start no treasure
    List<Treasure> treasures = new ArrayList<>();
    treasures.add(Treasure.SAPPHIRE); // Check one treasure exists at location
    Assert.assertEquals(treasures, q.getCurrentLocationTreasure());
    q.takeTreasure();
    player_treasures.add(Treasure.SAPPHIRE);
    treasures.remove(Treasure.SAPPHIRE);
    Assert.assertEquals(player_treasures, q.getPlayerTreasure()); // Check treasure added
    Assert.assertEquals(treasures, q.getCurrentLocationTreasure()); // Check treasure removed
  }

  @Test
  public void takeAndGetTreasureMultipleIncludingDuplicatesStack() {
    Dungeon k = new TreasureDungeon(14, 15, 2, true, 400, 4L);
    List<Treasure> player_treasures = new ArrayList<>();
    Assert.assertEquals(player_treasures, k.getPlayerTreasure()); // Check start no treasure
    List<Treasure> treasures = new ArrayList<>();
    treasures.add(Treasure.DIAMOND);
    treasures.add(Treasure.DIAMOND);
    treasures.add(Treasure.SAPPHIRE);
    treasures.add(Treasure.DIAMOND);
    Assert.assertEquals(treasures, k.getCurrentLocationTreasure());
    k.takeTreasure();
    Assert.assertEquals(treasures, k.getPlayerTreasure()); // Check treasure added
    k.movePlayer(Direction.SOUTH);
    k.takeTreasure();
    treasures.add(Treasure.DIAMOND);
    treasures.add(Treasure.DIAMOND);
    treasures.add(Treasure.DIAMOND);
    treasures.add(Treasure.RUBY);
    Assert.assertEquals(treasures, k.getPlayerTreasure()); // Check treasure added
  }

  @Test
  public void takeAndGetTreasureTwoTakesStack() {
    Dungeon q = new TreasureDungeon(14, 15, 100, true, 100, 4L);
    List<Treasure> player_treasures = new ArrayList<>();
    Assert.assertEquals(player_treasures, q.getPlayerTreasure()); // Check start no treasure
    List<Treasure> treasures = new ArrayList<>();
    q.movePlayer(Direction.NORTH);
    treasures.add(Treasure.DIAMOND); // Check one treasure exists at location
    Assert.assertEquals(treasures, q.getCurrentLocationTreasure());
    q.takeTreasure();
    player_treasures.add(Treasure.DIAMOND);
    treasures.remove(Treasure.DIAMOND);
    Assert.assertEquals(player_treasures, q.getPlayerTreasure()); // Check treasure added
    Assert.assertEquals(treasures, q.getCurrentLocationTreasure()); // Check treasure removed
    // Test move and take another
    q.movePlayer(Direction.NORTH);
    treasures.add(Treasure.RUBY); // Check one treasure exists at location
    Assert.assertEquals(treasures, q.getCurrentLocationTreasure());
    q.takeTreasure();
    player_treasures.add(Treasure.RUBY);
    treasures.remove(Treasure.RUBY);
    Assert.assertEquals(player_treasures, q.getPlayerTreasure()); // Check treasure added
    Assert.assertEquals(treasures, q.getCurrentLocationTreasure()); // Check treasure removed
  }

  @Test(expected = IllegalArgumentException.class)
  public void takeTreasureAfterGameOver() {
    Dungeon z = new TreasureDungeon(3, 4, 0, true, 120, 4L);
    // Move through all squares, one path
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.WEST);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.NORTH);
    z.takeTreasure();
  }

  @Test
  public void getVisited() {
    Dungeon z = new TreasureDungeon(3, 4, 0, true, 120, 4L);
    // Move through all squares, one path
    System.out.println(z.getVisitedLocations()[0][0].getVisited());
  }

}
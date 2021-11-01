import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import dungeon.Direction;
import dungeon.Dungeon;
import dungeon.Treasure;
import dungeon.TreasureDungeon;


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
  public void getDirectionsWrapped() {
    Set<Direction> d = new HashSet<>();
    d.add(Direction.NORTH);
    d.add(Direction.WEST);
    Assert.assertEquals(d,q.getDirections());
  }

  @Test
  public void getDirectionsNonWrapped() {
    Set<Direction> d = new HashSet<>();
    d.add(Direction.EAST);
    Assert.assertEquals(d,p.getDirections());
  }

  @Test
  public void movePlayerMaxInterconnectivityWrappedAllSquares() {
    Dungeon z = new TreasureDungeon(4,8,100,true, 120,4L);

    for (int i=0;i<8;i++) {
      z.movePlayer(Direction.WEST);
      for (int j=0;j<4;j++) {
        if (i!=4) { // Skip the ending square, so we can test all squares
          z.movePlayer(Direction.NORTH);
        }
      }
    }
    for (int i=0;i<4;i++) {
      z.movePlayer(Direction.NORTH);
      for (int j=0;j<8;j++) {
        System.out.println(i+" " + j);
        if ((i!= 1 && j!=6)) { // Skip the ending square, so we can test all squares
          z.movePlayer(Direction.WEST);
        }
      }
    }
  }

  @Test
  public void movePlayerMaxInterconnectivityUnWrappedAllSquares() {
    Dungeon z = new TreasureDungeon(3,4,100,false, 120,4L);
    // Move through all 9 squares, one path
    z.printGrid();
    System.out.println(z.getDirections());
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
    try {
      z.movePlayer(Direction.EAST);
    } catch (IllegalArgumentException e) {
    }
  }

  @Test
  public void movePlayerZeroInterconnectivityUnWrappedAllSquares() {
    Dungeon z = new TreasureDungeon(3,4,0,false, 120,4L);
    // Move through all squares, one path
    z.printGrid();
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
    try {
      z.movePlayer(Direction.NORTH);
    } catch (IllegalArgumentException e) {
      System.out.println(e + " expected.");
    }
  }

  @Test
  public void movePlayerZeroInterconnectivityWrappedAllSquares() {
    Dungeon z = new TreasureDungeon(3,4,0,true, 120,4L);
    // Move through all squares, one path
    z.printGrid();
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
    try {
      z.movePlayer(Direction.EAST);
    } catch (IllegalArgumentException e) {
      System.out.println(e + " expected.");
    }
  }

  @Test (expected = IllegalArgumentException.class)
  public void movePlayerUnWrappedEast() {
    Dungeon z = new TreasureDungeon(4,8,100,false, 120,4L);
    for (int i=0;i<40;i++) {
      z.movePlayer(Direction.EAST);
    }
  }

  @Test (expected = IllegalArgumentException.class)
  public void movePlayerNonWrappedWest() {
    Dungeon z = new TreasureDungeon(4,8,100,false, 120,4L);
    for (int i=0;i<40;i++) {
      z.movePlayer(Direction.WEST);
    }
  }

  @Test (expected = IllegalArgumentException.class)
  public void movePlayerNonWrappedNorth() {
    Dungeon z = new TreasureDungeon(4,8,100,false, 120,4L);
    for (int i=0;i<40;i++) {
      z.movePlayer(Direction.NORTH);
    }
  }

  @Test (expected = IllegalArgumentException.class)
  public void movePlayerNonWrappedSouth() {
    Dungeon z = new TreasureDungeon(4,8,100,false, 120,4L);
    for (int i=0;i<40;i++) {
      z.movePlayer(Direction.SOUTH);
    }
  }

  @Test
  public void getCurrentLocationNoTreasure() {
    Dungeon d = new TreasureDungeon(6,7,0,false,0,4L);
    List<Treasure> treasures = new ArrayList<>();
    Assert.assertEquals(treasures,d.getCurrentLocationTreasure());
  }

  @Test
  public void getCurrentLocationTreasureOne() {
    Dungeon d = new TreasureDungeon(6,7,0,false,100,4L);
    List<Treasure> treasures = new ArrayList<>();
    treasures.add(Treasure.RUBY);
    Assert.assertEquals(treasures,d.getCurrentLocationTreasure());
  }

  @Test
  public void getCurrentLocationTreasureMultiple() {
    Dungeon d = new TreasureDungeon(6,7,0,false,300,4L);
    List<Treasure> treasures = new ArrayList<>();
    treasures.add(Treasure.RUBY);
    treasures.add(Treasure.DIAMOND);
    treasures.add(Treasure.DIAMOND);
    Assert.assertEquals(treasures,d.getCurrentLocationTreasure());
  }

  @Test
  public void takeAndGetTreasureNone() {
    Dungeon k = new TreasureDungeon(14,15,2,true, 0,4L);
    List<Treasure> player_treasures = new ArrayList<>();
    Assert.assertEquals(player_treasures,k.getPlayerTreasure()); // Check start no treasure
    List<Treasure> treasures = new ArrayList<>();
    Assert.assertEquals(treasures,k.getCurrentLocationTreasure());
    k.takeTreasure();
    Assert.assertEquals(player_treasures,k.getPlayerTreasure()); // Check no treasure added
    Assert.assertEquals(treasures,k.getCurrentLocationTreasure()); // Check no treasure removed
  }

  @Test
  public void takeAndGetTreasureOne() {
    List<Treasure> player_treasures = new ArrayList<>();
    Assert.assertEquals(player_treasures,q.getPlayerTreasure()); // Check start no treasure
    List<Treasure> treasures = new ArrayList<>();
    treasures.add(Treasure.SAPPHIRE); // Check one treasure exists at location
    Assert.assertEquals(treasures,q.getCurrentLocationTreasure());
    q.takeTreasure();
    player_treasures.add(Treasure.SAPPHIRE);
    treasures.remove(Treasure.SAPPHIRE);
    Assert.assertEquals(player_treasures,q.getPlayerTreasure()); // Check treasure added
    Assert.assertEquals(treasures,q.getCurrentLocationTreasure()); // Check treasure removed
  }

  @Test
  public void takeAndGetTreasureMultipleIncludingDuplicates() {
    Dungeon k = new TreasureDungeon(14,15,2,true, 400,4L);
    List<Treasure> player_treasures = new ArrayList<>();
    Assert.assertEquals(player_treasures,k.getPlayerTreasure()); // Check start no treasure
    List<Treasure> treasures = new ArrayList<>();
    treasures.add(Treasure.RUBY);
    treasures.add(Treasure.SAPPHIRE);
    treasures.add(Treasure.DIAMOND);
    treasures.add(Treasure.RUBY);
    Assert.assertEquals(treasures,k.getCurrentLocationTreasure());
    k.takeTreasure();
    player_treasures.add(Treasure.RUBY);
    player_treasures.add(Treasure.SAPPHIRE);
    player_treasures.add(Treasure.DIAMOND);
    player_treasures.add(Treasure.RUBY);
    treasures.clear();
    Assert.assertEquals(player_treasures,k.getPlayerTreasure()); // Check treasure added
    Assert.assertEquals(treasures,k.getCurrentLocationTreasure()); // Check treasure removed
  }

  @Test
  public void takeAndGetTreasureTwoTakes() {
    List<Treasure> player_treasures = new ArrayList<>();
    Assert.assertEquals(player_treasures,q.getPlayerTreasure()); // Check start no treasure
    List<Treasure> treasures = new ArrayList<>();
    treasures.add(Treasure.SAPPHIRE); // Check one treasure exists at location
    Assert.assertEquals(treasures,q.getCurrentLocationTreasure());
    q.takeTreasure();
    player_treasures.add(Treasure.SAPPHIRE);
    treasures.remove(Treasure.SAPPHIRE);
    Assert.assertEquals(player_treasures,q.getPlayerTreasure()); // Check treasure added
    Assert.assertEquals(treasures,q.getCurrentLocationTreasure()); // Check treasure removed
    // Test move and take another
    q.movePlayer(Direction.WEST);
    treasures.add(Treasure.DIAMOND); // Check one treasure exists at location
    Assert.assertEquals(treasures,q.getCurrentLocationTreasure());
    q.takeTreasure();
    player_treasures.add(Treasure.DIAMOND);
    treasures.remove(Treasure.DIAMOND);
    Assert.assertEquals(player_treasures,q.getPlayerTreasure()); // Check treasure added
    Assert.assertEquals(treasures,q.getCurrentLocationTreasure()); // Check treasure removed
  }

  @Test (expected = IllegalArgumentException.class)
  public void moveAfterGameOver() {
    Dungeon t = new TreasureDungeon(3,3,100,true,
            400,4L);
    t.movePlayer(Direction.SOUTH);
    t.movePlayer(Direction.SOUTH);
    t.movePlayer(Direction.SOUTH);
  }

  @Test (expected = IllegalArgumentException.class)
  public void getTreasureAfterGameOver() {
    Dungeon t = new TreasureDungeon(3,3,100,true,
            400,4L);
    t.movePlayer(Direction.SOUTH);
    t.movePlayer(Direction.SOUTH);
    t.takeTreasure();
  }

}
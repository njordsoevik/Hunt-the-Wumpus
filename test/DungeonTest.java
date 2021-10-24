import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;


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
  public void movePlayerWrapped() {
    q.movePlayer(Direction.WEST);
    Set<Direction> d = new HashSet<>();
    d.add(Direction.NORTH);
    d.add(Direction.EAST);
    d.add(Direction.SOUTH);
    Assert.assertEquals(d,q.getDirections());
    q.movePlayer(Direction.SOUTH);
    d.remove(Direction.SOUTH);
    d.add(Direction.WEST);
    Assert.assertEquals(d,q.getDirections());
    q.movePlayer(Direction.NORTH); // Test moving back to old square
    d.remove(Direction.WEST);
    d.add(Direction.SOUTH);
    Assert.assertEquals(d,q.getDirections());
  }

  @Test
  public void movePlayerNonWrapped() {
    System.out.println(p.getDirections());
    Set<Direction> d = new HashSet<>();
    d.add(Direction.EAST);
    Assert.assertEquals(d,p.getDirections());
    p.movePlayer(Direction.EAST);
    p.movePlayer(Direction.WEST);
    Assert.assertEquals(d,p.getDirections());// Test moving back to old square
  }

  @Test
  public void getCurrentLocationNoTreasure() {
    Dungeon d = new TreasureDungeon(6,7,0,false,0,4L);
    List<Treasure> treasures = new ArrayList<>();
    Assert.assertEquals(treasures,d.getCurrentLocationTreasure());
  }

  @Test
  public void getCurrentLocationOneTreasure() {
    Dungeon d = new TreasureDungeon(6,7,0,false,100,4L);
    List<Treasure> treasures = new ArrayList<>();
    treasures.add(Treasure.RUBY);
    Assert.assertEquals(treasures,d.getCurrentLocationTreasure());
  }

  @Test
  public void getCurrentLocationMultipleTreasure() {
    Dungeon d = new TreasureDungeon(6,7,0,false,300,4L);
    List<Treasure> treasures = new ArrayList<>();
    treasures.add(Treasure.RUBY);
    treasures.add(Treasure.DIAMOND);
    treasures.add(Treasure.DIAMOND);
    Assert.assertEquals(treasures,d.getCurrentLocationTreasure());
  }

  @Test
  public void takeTreasure() {
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
  public void getPlayerTreasure() {
  }


}
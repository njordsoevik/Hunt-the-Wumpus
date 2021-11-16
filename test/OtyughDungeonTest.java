import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dungeon.model.Arrow;
import dungeon.model.Direction;
import dungeon.model.Health;
import dungeon.model.OtyughDungeon;
import dungeon.model.OtyughTreasureDungeon;
import dungeon.model.Smell;
import dungeon.controller.DungeonConsoleController;
import dungeon.controller.DungeonController;

/**
 * A testing class for the OtyughDungeon interface.
 */
public class OtyughDungeonTest {

  @Test(expected = IllegalArgumentException.class)
  public void addOneTooManyOtyughs() {
    new OtyughTreasureDungeon(3, 4, 0, false, 20, 10, 8, 5L);
  }

  @Test
  public void placeExactAmountOtyughs() {
    new OtyughTreasureDungeon(3, 4, 0, false, 20, 10, 7, 5L);
  }

  @Test
  public void checkOneOtyughLastCaveEnd() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 10, 1, 5L);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.EAST);
    z.shootArrow(Direction.EAST, 1);
    z.shootArrow(Direction.EAST, 1);
    z.movePlayer(Direction.EAST);
    Assert.assertEquals(z.isGameOver(), true);
    Assert.assertEquals(z.getPlayerHealth(), Health.HEALTHY);
  }

  @Test
  public void checkStartingArrows() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 100, 7, 5L);
    List<Arrow> a = new ArrayList<>();
    a.add(Arrow.CROOKED);
    a.add(Arrow.CROOKED);
    a.add(Arrow.CROOKED);
    Assert.assertEquals(z.getPlayerArrows(), a);
  }

  @Test
  public void checkShootingArrowsUpdatesInventory() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 100, 7, 5L);
    List<Arrow> a = new ArrayList<>();
    a.add(Arrow.CROOKED);
    a.add(Arrow.CROOKED);
    a.add(Arrow.CROOKED);
    Assert.assertEquals(z.getPlayerArrows(), a);
    z.shootArrow(Direction.SOUTH, 1);
    a.remove(0);
    Assert.assertEquals(z.getPlayerArrows(), a);
    z.shootArrow(Direction.SOUTH, 1);
    a.remove(0);
    Assert.assertEquals(z.getPlayerArrows(), a);
    z.shootArrow(Direction.SOUTH, 1);
    a.remove(0);
    Assert.assertEquals(z.getPlayerArrows(), a);
    try {
      z.shootArrow(Direction.SOUTH, 1);
    } catch (IllegalArgumentException ex) {
      Assert.assertEquals(z.getPlayerArrows(), a);
    }
  }

  @Test
  public void checkLocationArrowsAndTakeOne() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 100, 7, 5L);
    List<Arrow> a = new ArrayList<>();
    a.add(Arrow.CROOKED);
    Assert.assertEquals(z.getCurrentLocationArrows(), a);
    z.takeArrows();
    a.add(Arrow.CROOKED);
    a.add(Arrow.CROOKED);
    a.add(Arrow.CROOKED);
    Assert.assertEquals(z.getPlayerArrows(), a);
  }

  @Test
  public void checkLocationArrowsTwo() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 200, 7, 5L);
    List<Arrow> a = new ArrayList<>();
    a.add(Arrow.CROOKED);
    a.add(Arrow.CROOKED);
    Assert.assertEquals(z.getCurrentLocationArrows(), a);
    z.takeArrows();
    a.add(Arrow.CROOKED);
    a.add(Arrow.CROOKED);
    a.add(Arrow.CROOKED);
    Assert.assertEquals(z.getPlayerArrows(), a);
  }

  @Test
  public void checkOtyughHealthyKillsPlayer() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 200, 7, 5L);
    Assert.assertEquals(Health.HEALTHY, z.getPlayerHealth());
    z.movePlayer(Direction.SOUTH);
    Assert.assertEquals(Health.DEAD, z.getPlayerHealth());
    try {
      z.movePlayer(Direction.NORTH);
    } catch (IllegalArgumentException ex) {
      Assert.assertEquals(true, z.isGameOver());
    }
  }

  @Test
  public void checkOtyughInjuredCanSurviveVisit() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 200, 7, 5L);
    z.shootArrow(Direction.SOUTH, 1);
    // Otyugh injured, run back and forth and survive four times
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.NORTH);
    // Fifth time kills
    try {
      z.movePlayer(Direction.SOUTH);
      z.movePlayer(Direction.NORTH);
    } catch (IllegalArgumentException ex) {
      Assert.assertEquals(true, z.isGameOver());
      Assert.assertEquals(Health.DEAD, z.getPlayerHealth());
    }
  }

  @Test
  public void checkOtyughDeadDoesNotKill() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 200, 7, 5L);
    Assert.assertEquals(Health.HEALTHY, z.getPlayerHealth());
    z.shootArrow(Direction.SOUTH, 1);
    z.shootArrow(Direction.SOUTH, 1);
    for (int i = 0; i < 100; i++) {
      z.movePlayer(Direction.SOUTH);
      z.movePlayer(Direction.NORTH);
    }
    Assert.assertEquals(Health.HEALTHY, z.getPlayerHealth());
  }

  @Test
  public void testShootingAndSmellUpdates() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 10000, 7, 5L);
    z.takeArrows();
    // One Otyugh nearby
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    z.shootArrow(Direction.SOUTH, 1);
    z.shootArrow(Direction.SOUTH, 1);
    // Does not update after killing Otyugh nearby, because two far ones
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    z.shootArrow(Direction.SOUTH, 2);
    z.shootArrow(Direction.SOUTH, 2);
    // One Otyugh far
    Assert.assertEquals(Smell.LESS_PUNGENT, z.getSmell());
    z.movePlayer(Direction.SOUTH);
    // One Otyugh close after moving closer
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    z.shootArrow(Direction.EAST, 1);
    // Injuring Otyugh does not change smell
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    // Killing Otyugh gets rid of all smell
    z.shootArrow(Direction.EAST, 1);
    Assert.assertEquals(Smell.NONE, z.getSmell());
    // Move closer to Otyugh to get faint smell
    z.movePlayer(Direction.EAST);
    Assert.assertEquals(Smell.LESS_PUNGENT, z.getSmell());
    // BENDING ARROWS: Shoot north and goes North -> East, kill Otyugh
    z.shootArrow(Direction.NORTH, 2);
    z.shootArrow(Direction.NORTH, 2);
    Assert.assertEquals(Smell.NONE, z.getSmell());
    // Move close to Otyugh then away to get full smell range
    z.movePlayer(Direction.NORTH);
    Assert.assertEquals(Smell.LESS_PUNGENT, z.getSmell());
    z.movePlayer(Direction.EAST);
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    z.movePlayer(Direction.WEST);
    Assert.assertEquals(Smell.LESS_PUNGENT, z.getSmell());
    z.movePlayer(Direction.SOUTH);
    Assert.assertEquals(Smell.NONE, z.getSmell());
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.EAST);
    // Test overshooting does not kill
    z.shootArrow(Direction.EAST, 5);
    z.shootArrow(Direction.EAST, 5);
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    // Test undershooting does not kill
    z.shootArrow(Direction.EAST, 0);
    z.shootArrow(Direction.EAST, 0);
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    // Test shooting into the wall is valid and does not kill
    z.shootArrow(Direction.NORTH, 0);
    z.shootArrow(Direction.NORTH, 0);
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    // Kill Otyugh and win game
    z.shootArrow(Direction.EAST, 1);
    z.shootArrow(Direction.EAST, 1);
    Assert.assertEquals(Smell.NONE, z.getSmell());
    z.movePlayer(Direction.EAST);
    Assert.assertTrue(z.isGameOver());
    Assert.assertEquals(z.getPlayerHealth(), Health.HEALTHY);
  }

  @Test
  public void smellOtyughs() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 1000, 5, 5L);
    z.takeArrows();
    // One in next room, two in two room away
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    z.shootArrow(Direction.SOUTH, 1);
    // One injured in next room, two in two room away
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    z.shootArrow(Direction.SOUTH, 1);
    // One dead in next room, two in two room away
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    z.shootArrow(Direction.SOUTH, 2);
    z.shootArrow(Direction.SOUTH, 2);
    // One dead in next room, one alive two rooms away
    Assert.assertEquals(Smell.LESS_PUNGENT, z.getSmell());
    // Move closer to the alive one
    z.movePlayer(Direction.SOUTH);
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    // Kill last nearby one
    z.shootArrow(Direction.EAST, 1);
    z.shootArrow(Direction.EAST, 1);
    Assert.assertEquals(Smell.NONE, z.getSmell());
  }

  // Controller Tests

  @Test(expected = IllegalArgumentException.class)
  public void invalidModelController() {
    Readable testInput = new StringReader("m south p s south 5");
    Appendable outputLog = new StringBuilder();
    OtyughDungeon m = null;
    DungeonController c = new DungeonConsoleController(testInput, outputLog);
    c.playGame(m);
  }

  @Test
  public void controllerMove() {
    Readable testInput = new StringReader("m south");
    Appendable outputLog = new StringBuilder();
    OtyughDungeon m = new OtyughTreasureDungeon(3, 4, 0, false, 120, 50, 2, 5L);
    DungeonController c = new DungeonConsoleController(testInput, outputLog);
    c.playGame(m);
    Set directions = new HashSet();
    directions.add(Direction.SOUTH);
    directions.add(Direction.EAST);
    directions.add(Direction.NORTH);
    Assert.assertEquals(m.getDirections(), directions);
  }


  @Test
  public void controllerTestCapitalLetters() {
    Readable testInput = new StringReader("M south P S east 1");
    Appendable outputLog = new StringBuilder();
    OtyughDungeon m = new OtyughTreasureDungeon(3, 4, 0, false, 120, 50, 2, 5L);
    DungeonController c = new DungeonConsoleController(testInput, outputLog);
    c.playGame(m);
    Set directions = new HashSet();
    directions.add(Direction.SOUTH);
    directions.add(Direction.EAST);
    directions.add(Direction.NORTH);
    Assert.assertEquals(m.getDirections(), directions);
  }

  @Test
  public void testControllerShootingAndSmell() {
    Readable testInput = new StringReader("");
    Appendable outputLog = new StringBuilder();
    OtyughDungeon m = new OtyughTreasureDungeon(3, 4, 0, false, 20, 10000, 7, 5L);
    DungeonController c = new DungeonConsoleController(testInput, outputLog);
    c.playGame(m);
    Assert.assertEquals(m.getSmell(), Smell.MORE_PUNGENT);
    // test pick-up/shooting with upper-case letters, abbreviated direction and full direction
    testInput = new StringReader("P S s 1 s south 1");
    c = new DungeonConsoleController(testInput, outputLog);
    c.playGame(m);
    // test pick-up/shooting with under-case letters, abbreviated direction and full direction
    Assert.assertEquals(m.getSmell(), Smell.MORE_PUNGENT);
    testInput = new StringReader("s s 2 s south 2");
    c = new DungeonConsoleController(testInput, outputLog);
    c.playGame(m);
    Assert.assertEquals(m.getSmell(), Smell.LESS_PUNGENT);
    testInput = new StringReader("m s s e 1 s e 1");
    c = new DungeonConsoleController(testInput, outputLog);
    c.playGame(m);
    Assert.assertEquals(m.getSmell(), Smell.NONE);
  }

  @Test
  public void testControllerEndGameWin() {
    Readable testInput = new StringReader("m s m e m s m e s e 1 s e 1 m e");
    Appendable outputLog = new StringBuilder();
    OtyughDungeon m = new OtyughTreasureDungeon(3, 4, 0, false, 20, 10, 1, 5L);
    DungeonController c = new DungeonConsoleController(testInput, outputLog);
    c.playGame(m);
    Assert.assertEquals(m.isGameOver(), true);
    Assert.assertEquals(m.getPlayerHealth(), Health.HEALTHY);
  }

  @Test
  public void testControllerEndGameLose() {
    Readable testInput = new StringReader("m s m e m s m e m e");
    Appendable outputLog = new StringBuilder();
    OtyughDungeon m = new OtyughTreasureDungeon(3, 4, 0, false, 20, 10, 1, 5L);
    DungeonController c = new DungeonConsoleController(testInput, outputLog);
    c.playGame(m);
    Assert.assertEquals(m.isGameOver(), true);
    Assert.assertEquals(m.getPlayerHealth(), Health.DEAD);
  }

}

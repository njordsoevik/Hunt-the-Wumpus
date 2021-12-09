import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import dungeon.controller.DungeonConsoleController;
import dungeon.controller.DungeonController;
import dungeon.controller.DungeonViewController;
import dungeon.controller.Features;
import dungeon.model.Arrow;
import dungeon.model.Direction;
import dungeon.model.Health;
import dungeon.model.OtyughDungeon;
import dungeon.model.OtyughTreasureDungeon;
import dungeon.model.Smell;
import dungeon.model.Treasure;
import dungeon.model.TreasureDungeon;
import dungeon.view.DungeonSwingView;
import dungeon.view.DungeonView;

/**
 * A testing class for the OtyughDungeon interface.
 */
public class OtyughDungeonTest {

  @Test(expected = IllegalArgumentException.class)
  public void createInvalidDungeonArrowPercentMax() {
    new OtyughTreasureDungeon(3, 4, 0, false, 20, Integer.MAX_VALUE + 1, 7, 5L);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createInvalidDungeonArrowPercentUnder0() {
    new OtyughTreasureDungeon(3, 4, 0, false, 20, -1, 7, 5L);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createInvalidDungeonNumberOtyughMax() {
    new OtyughTreasureDungeon(3, 4, 0, false, 20, 5, Integer.MAX_VALUE + 1, 5L);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createInvalidDungeonNumberOtyughUnder0() {
    new OtyughTreasureDungeon(3, 4, 0, false, 20, 5, -1, 5L);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addOneTooManyOtyughs() {
    new OtyughTreasureDungeon(3, 4, 0, false, 20, 10, 8, 5L);
  }

  @Test
  public void placeExactAmountOtyughs() {
    TreasureDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 10, 7, 5L);
    Assert.assertEquals(false, z.isGameOver());
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
      z.shootArrow(Direction.SOUTH, 1); // Shooting after running out of arrows
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
  public void checkArrowsCorrectPercentage() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 100, 1, 5L);
    z.takeArrows();
    z.movePlayer(Direction.SOUTH);
    z.takeArrows();
    z.movePlayer(Direction.SOUTH);
    z.takeArrows();
    z.movePlayer(Direction.NORTH);
    z.takeArrows();
    z.movePlayer(Direction.EAST);
    z.takeArrows();
    z.movePlayer(Direction.NORTH);
    z.takeArrows();
    z.movePlayer(Direction.EAST);
    z.takeArrows();
    z.movePlayer(Direction.EAST);
    z.takeArrows();
    z.movePlayer(Direction.WEST);
    z.takeArrows();
    z.movePlayer(Direction.SOUTH);
    z.takeArrows();
    z.movePlayer(Direction.EAST);
    z.takeArrows();
    z.movePlayer(Direction.WEST);
    z.takeArrows();
    z.movePlayer(Direction.NORTH);
    z.takeArrows();
    z.movePlayer(Direction.WEST);
    z.takeArrows();
    z.movePlayer(Direction.SOUTH);
    z.takeArrows();
    z.movePlayer(Direction.SOUTH);
    z.takeArrows();
    z.movePlayer(Direction.EAST);
    z.takeArrows();
    Assert.assertEquals(14, z.getPlayerArrows().size()); // 3(Starting)+12(Squares)-1(Ending)
  }

  @Test
  public void checkThief() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 200, 100, 1, 5L);
    System.out.println(z);
    z.takeTreasure();
    Assert.assertTrue(z.getPlayerTreasure().contains(Treasure.SAPPHIRE));
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.EAST);
    z.movePlayer(Direction.NORTH);
    z.movePlayer(Direction.EAST);
    // Thief steals once
    Assert.assertTrue(z.getPlayerTreasure().isEmpty());
    z.movePlayer(Direction.EAST);
    z.takeTreasure();
    z.movePlayer(Direction.WEST);
    // Thief won't steal on second visit
    Assert.assertTrue(z.getPlayerTreasure().contains(Treasure.SAPPHIRE));
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
    // Otyugh injured, run back and forth and survive
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
  public void checkOtyughIsInCaveNotTunnels() {
    OtyughDungeon z = new OtyughTreasureDungeon(10, 10, 0, false, 20, 200, 7);
    for (int i = 0; i < 50; i++) {
      while (!z.isGameOver()) {
        Random r = new Random();
        Direction move = (Direction) z.getDirections()
                .toArray()[r.nextInt(z.getDirections().size())];
        z.movePlayer(move);
      }
      if (z.getPlayerHealth() == Health.DEAD) {
        Assert.assertTrue(z.getDirections().size() != 2);
      }
    }
  }

  @Test
  public void checkOtyughNotInStart() {
    for (int i = 0; i < 100; i++) {
      OtyughDungeon z = new OtyughTreasureDungeon(5, 5, 0, false, 20, 200, 1);
      Direction move = (Direction) z.getDirections().toArray()[0];
      z.movePlayer(move);
      z.movePlayer(move.getInverse());
      Assert.assertEquals(false, z.isGameOver());
    }
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
  public void testShootingAllDirections() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 10000, 7, 5L);
    z.takeArrows();
    z.shootArrow(Direction.SOUTH, 1);
    z.shootArrow(Direction.WEST, 1);
    z.shootArrow(Direction.EAST, 1);
    z.shootArrow(Direction.NORTH, 1);
    Assert.assertEquals(z.isGameOver(), false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShootingNegativeDistance() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 10000, 7, 5L);
    z.takeArrows();
    z.shootArrow(Direction.SOUTH, -1);
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
    // SHOOT STRAIGHT OVER DISTANCE TEST
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
    // BENDING ARROWS TEST: Shoot north and goes North -> East, kill Otyugh and reduces smell to
    // none
    z.shootArrow(Direction.NORTH, 2);
    z.shootArrow(Direction.NORTH, 2);
    Assert.assertEquals(Smell.NONE, z.getSmell());
    z.movePlayer(Direction.NORTH);
    // Verify we shot through a tunnel and the arrow must have exited EAST
    Assert.assertEquals(2, z.getDirections().size());
    // Move close to Otyugh then away to get full smell range
    Assert.assertEquals(Smell.LESS_PUNGENT, z.getSmell());
    z.movePlayer(Direction.EAST);
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    z.movePlayer(Direction.WEST);
    Assert.assertEquals(Smell.LESS_PUNGENT, z.getSmell());
    z.movePlayer(Direction.SOUTH);
    Assert.assertEquals(Smell.NONE, z.getSmell());
    z.movePlayer(Direction.SOUTH);
    z.movePlayer(Direction.EAST);
    // TEST OVERSHOOTING does not kill
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    z.shootArrow(Direction.EAST, 5);
    z.shootArrow(Direction.EAST, 5);
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    // TEST UNDERSHOOTING does not kill
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    z.shootArrow(Direction.EAST, 0);
    z.shootArrow(Direction.EAST, 0);
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    // TEST SHOOTING INTO THE WALL is valid and does not kill
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
    OtyughDungeon z = new OtyughTreasureDungeon(4, 4, 0, false, 20, 1000, 5, 5L);
    z.takeArrows();
    // One in next room, two in two room away
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    z.shootArrow(Direction.EAST, 1);
    // One injured in next room, two in two room away
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
    z.shootArrow(Direction.EAST, 1);
    // One dead in next room, two in two room away
    Assert.assertEquals(Smell.NONE, z.getSmell());
  }

  @Test
  public void smellOtyughWhenInSameCave() {
    OtyughDungeon z = new OtyughTreasureDungeon(3, 4, 0, false, 20, 1000, 2, 5L);
    z.takeArrows();
    z.shootArrow(Direction.SOUTH, 1);
    z.movePlayer(Direction.SOUTH);
    // Player stepped in cave with injured Otyugh
    Assert.assertEquals(Smell.MORE_PUNGENT, z.getSmell());
  }

  // Controller Tests
  @Test(expected = IllegalArgumentException.class)
  public void invalidModelController() {
    Readable testInput = new StringReader("m south p s south 5");
    Appendable outputLog = new StringBuilder();
    OtyughDungeon m = null;
    DungeonController c = new DungeonConsoleController(m, testInput, outputLog);
    c.playGame();
  }


  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    OtyughDungeon m = new OtyughTreasureDungeon(3, 4, 0, false, 120, 50, 2, 5L);
    StringReader input = new StringReader("m south");
    Appendable gameLog = new FailingAppendable();
    DungeonController c = new DungeonConsoleController(m, input, gameLog);
    c.playGame();
  }

  @Test
  public void testModelGetsCorrectInput() {
    OtyughDungeon m = new MockModel();
    StringReader input = new StringReader("m south");
    Appendable outputLog = new StringBuilder();
    DungeonController c = new DungeonConsoleController(m, input, outputLog);
    c.playGame();
    Assert.assertEquals(Direction.SOUTH.toString(), ((MockModel) m).inputOne);
  }

  @Test
  public void controllerMoveAllDirections() {
    Readable testInput = new StringReader("m south m east m west m north");
    Appendable outputLog = new StringBuilder();
    OtyughDungeon m = new OtyughTreasureDungeon(10, 10, 1000, true, 120, 50, 1, 5L);
    DungeonController c = new DungeonConsoleController(m, testInput, outputLog);
    c.playGame();
    Set directions = new HashSet();
    directions.add(Direction.SOUTH);
    directions.add(Direction.EAST);
    directions.add(Direction.NORTH);
    directions.add(Direction.WEST);
    Assert.assertEquals(m.getDirections(), directions);
  }

  @Test
  public void controllerShootAllDirections() {
    Readable testInput = new StringReader("p s south 5 s east 5 s west 5 s north 5");
    Appendable outputLog = new StringBuilder();
    OtyughDungeon m = new OtyughTreasureDungeon(10, 10, 1000, true, 120, 200, 1, 5L);
    DungeonController c = new DungeonConsoleController(m, testInput, outputLog);
    c.playGame();
    Assert.assertEquals(m.isGameOver(), false);
  }

  @Test
  public void controllerTestCapitalLetters() {
    Readable testInput = new StringReader("M south P S east 1");
    Appendable outputLog = new StringBuilder();
    OtyughDungeon m = new OtyughTreasureDungeon(3, 4, 0, false, 120, 50, 2, 5L);
    DungeonController c = new DungeonConsoleController(m, testInput, outputLog);
    c.playGame();
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
    DungeonController c = new DungeonConsoleController(m, testInput, outputLog);
    c.playGame();
    Assert.assertEquals(m.getSmell(), Smell.MORE_PUNGENT);
    // test pick-up/shooting with upper-case letters, abbreviated direction and full direction
    testInput = new StringReader("P S s 1 s south 1");
    c = new DungeonConsoleController(m, testInput, outputLog);
    c.playGame();
    // test pick-up/shooting with under-case letters, abbreviated direction and full direction
    Assert.assertEquals(m.getSmell(), Smell.MORE_PUNGENT);
    testInput = new StringReader("s s 2 s south 2");
    c = new DungeonConsoleController(m, testInput, outputLog);
    c.playGame();
    Assert.assertEquals(m.getSmell(), Smell.LESS_PUNGENT);
    testInput = new StringReader("m s s e 1 s e 1");
    c = new DungeonConsoleController(m, testInput, outputLog);
    c.playGame();
    Assert.assertEquals(m.getSmell(), Smell.NONE);
  }

  @Test
  public void testControllerPickUpAndGetInformationAfterPickUp() {
    Readable testInput = new StringReader("p i");
    Appendable outputLog = new StringBuilder();
    OtyughDungeon m = new OtyughTreasureDungeon(3, 4, 0, false, 200, 200, 1, 5L);
    DungeonController c = new DungeonConsoleController(m, testInput, outputLog);
    c.playGame();
    String test = "\n"
            + "\n"
            + "Doors lead to: SOUTH\n"
            + "The cave you are in holds arrows:  CROOKED CROOKED\n"
            + "The cave you are in holds treasures: SAPPHIRE SAPPHIRE\n"
            + "There is no smell in the air\n"
            + "\n"
            + "Move, Pickup, Shoot, or Player Information? (M-P-S-I) \n"
            + "You have picked up any treasures or arrows\n"
            + "\n"
            + "\n" + "Doors lead to: SOUTH\n" + "There is no smell in the air\n" + "\n"
            + "Move, Pickup, Shoot, or Player Information? (M-P-S-I) \n"
            + "You are holding arrows:  CROOKED CROOKED CROOKED CROOKED CROOKED\n"
            + "You are holding treasures:  SAPPHIRE SAPPHIRE\n" + "\n" + "\n"
            + "Doors lead to: SOUTH\n" + "There is no smell in the air\n" + "\n"
            + "Move, Pickup, Shoot, or Player Information? (M-P-S-I) \n";
    Assert.assertEquals(outputLog.toString(), test);
  }

  @Test
  public void testControllerInvalidMove() {
    Readable testInput = new StringReader("m o");
    Appendable outputLog = new StringBuilder();
    OtyughDungeon m = new OtyughTreasureDungeon(3, 4, 0, false, 200, 200, 1, 5L);
    DungeonController c = new DungeonConsoleController(m, testInput, outputLog);
    c.playGame();
    String test = "\n" +
            "\n" +
            "Doors lead to: SOUTH\n" +
            "The cave you are in holds arrows:  CROOKED CROOKED\n" +
            "The cave you are in holds treasures: SAPPHIRE SAPPHIRE\n" +
            "There is no smell in the air\n" +
            "\n" +
            "Move, Pickup, Shoot, or Player Information? (M-P-S-I) \n" +
            "Choose direction: \n" +
            "Cannot move there from this location.\n" +
            "\n" +
            "Doors lead to: SOUTH\n" +
            "The cave you are in holds arrows:  CROOKED CROOKED\n" +
            "The cave you are in holds treasures: SAPPHIRE SAPPHIRE\n" +
            "There is no smell in the air\n" +
            "\n" +
            "Move, Pickup, Shoot, or Player Information? (M-P-S-I) \n";
    Assert.assertEquals(outputLog.toString(), test);
  }

  @Test
  public void testControllerInvalidShoot() {
    Readable testInput = new StringReader("s o 4");
    Appendable outputLog = new StringBuilder();
    OtyughDungeon m = new OtyughTreasureDungeon(3, 4, 0, false, 200, 200, 1, 5L);
    DungeonController c = new DungeonConsoleController(m, testInput, outputLog);
    c.playGame();
    String test = "\n" +
            "\n" +
            "Doors lead to: SOUTH\n" +
            "The cave you are in holds arrows:  CROOKED CROOKED\n" +
            "The cave you are in holds treasures: SAPPHIRE SAPPHIRE\n" +
            "There is no smell in the air\n" +
            "\n" +
            "Move, Pickup, Shoot, or Player Information? (M-P-S-I) \n" +
            "Choose direction: \n" +
            "Choose distance: \n" +
            "Cannot shoot in that direction or distance.\n" +
            "\n" +
            "Doors lead to: SOUTH\n" +
            "The cave you are in holds arrows:  CROOKED CROOKED\n" +
            "The cave you are in holds treasures: SAPPHIRE SAPPHIRE\n" +
            "There is no smell in the air\n" +
            "\n" +
            "Move, Pickup, Shoot, or Player Information? (M-P-S-I) \n";
    Assert.assertEquals(outputLog.toString(), test);
  }

  @Test
  public void testControllerEndGameWin() {
    Readable testInput = new StringReader("m s m e m s m e s e 1 s e 1 m e");
    Appendable outputLog = new StringBuilder();
    OtyughDungeon m = new OtyughTreasureDungeon(3, 4, 0, false, 20, 10, 1, 5L);
    DungeonController c = new DungeonConsoleController(m, testInput, outputLog);
    c.playGame();
    Assert.assertEquals(m.isGameOver(), true);
    Assert.assertTrue(outputLog.toString().contains("End square reached, game over!"));
    Assert.assertEquals(m.getPlayerHealth(), Health.HEALTHY);
  }

  @Test
  public void testControllerEndGameLose() {
    Readable testInput = new StringReader("m s m e m s m e m e");
    Appendable outputLog = new StringBuilder();
    OtyughDungeon m = new OtyughTreasureDungeon(3, 4, 0, false, 20, 10, 1, 5L);
    DungeonController c = new DungeonConsoleController(m, testInput, outputLog);
    c.playGame();
    Assert.assertEquals(m.isGameOver(), true);
    Assert.assertTrue(outputLog.toString().contains("An Otyugh has eaten you! Game over."));
    Assert.assertEquals(m.getPlayerHealth(), Health.DEAD);
  }


  // View Controller Tests
  @Test (expected = IllegalArgumentException.class)
  public void invalidModelViewController() {
    OtyughDungeon m = null;
    DungeonView view = new DungeonSwingView(m);
    DungeonController controller = new DungeonViewController(m, view, 5, 5,
            0, false, 150, 50, 1);
    controller.playGame();
  }

  @Test
  public void testViewInitializes() {
    OtyughDungeon m = new OtyughTreasureDungeon(10, 10,
            10, false, 150, 50, 1, 5L);
    MockView view = new MockView();
    DungeonController controller = new DungeonViewController(m, view, 10, 10,
            10, false, 150, 50, 1, 5L);
    controller.playGame();
    StringBuilder testBuilder = new StringBuilder();
    testBuilder.append("setFeatures").append("makeVisible").append("resetFocus");

    Assert.assertEquals(testBuilder.toString(), view.methodCalled.toString());
  }

  @Test
  public void testViewMoveFeatures() {
    OtyughDungeon m = new OtyughTreasureDungeon(10, 10,
            10, false, 150, 50, 1, 5L);
    MockView view = new MockView();
    Features controller = new DungeonViewController(m, view, 10, 10,
            10, false, 150, 50, 1, 5L);
    StringBuilder testBuilder = new StringBuilder();

    // INVALID DIRECTION
    controller.move(Direction.NORTH);
    testBuilder.append("showErrorMessage");
    // VALID DIRECTION
    controller.move(Direction.SOUTH);
    testBuilder.append("refreshresetFocus");

    Assert.assertEquals(testBuilder.toString(), view.methodCalled.toString());
  }

  @Test
  public void testViewShootFeatures() {
    OtyughDungeon m = new OtyughTreasureDungeon(10, 10,
            10, false, 150, 50, 1, 5L);
    MockView view = new MockView();
    Features controller = new DungeonViewController(m, view, 10, 10,
            10, false, 150, 50, 1, 5L);
    StringBuilder testBuilder = new StringBuilder();

    // INVALID DIRECTION
    controller.shootArrow(null, 5);
    testBuilder.append("showErrorMessage");
    // INVALID DISTANCE
    controller.shootArrow(Direction.NORTH, -5);
    testBuilder.append("showErrorMessage");
    // VALID DIRECTION
    controller.shootArrow(Direction.NORTH, 5);
    testBuilder.append("refreshresetFocus");

    Assert.assertEquals(testBuilder.toString(), view.methodCalled.toString());
  }

  @Test
  public void testViewPickUpFeatures() {
    OtyughDungeon m = new OtyughTreasureDungeon(10, 10,
            10, false, 150, 50, 1, 5L);
    MockView view = new MockView();
    Features controller = new DungeonViewController(m, view, 10, 10,
            10, false, 150, 50, 1, 5L);
    StringBuilder testBuilder = new StringBuilder();

    controller.pickUp();
    testBuilder.append("refreshresetFocus");

    Assert.assertEquals(testBuilder.toString(), view.methodCalled.toString());
  }

  @Test
  public void testViewRestartAndExitFeatures() {
    OtyughDungeon m = new OtyughTreasureDungeon(10, 10,
            10, false, 150, 50, 1, 5L);
    MockView view = new MockView();
    Features controller = new DungeonViewController(m, view, 10, 10,
            10, false, 150, 50, 1, 5L);
    StringBuilder testBuilder = new StringBuilder();

    controller.restartProgram();
    testBuilder.append("updateModelrefreshresetFocus");
    Assert.assertEquals(testBuilder.toString(), view.methodCalled.toString());
    controller.exitProgram();
    // Exit adds no method calls
    Assert.assertEquals(testBuilder.toString(), view.methodCalled.toString());
  }

  @Test
  public void testViewValidProcessInputFeatures() {
    OtyughDungeon m = new OtyughTreasureDungeon(10, 10,
            10, false, 150, 50, 1, 5L);
    MockView view = new MockView();
    Features controller = new DungeonViewController(m, view, 10, 10,
            10, false, 150, 50, 1, 5L);
    StringBuilder testBuilder = new StringBuilder();

    controller.processInput("10", "10",
            "10", "false", "150", "50", "1");
    testBuilder.append("updateModelrefreshresetFocus");
    Assert.assertEquals(testBuilder.toString(), view.methodCalled.toString());
  }

  @Test
  public void testViewInvalidProcessInputFeatures() {
    OtyughDungeon m = new OtyughTreasureDungeon(10, 10,
            10, false, 150, 50, 1, 5L);
    MockView view = new MockView();
    Features controller = new DungeonViewController(m, view, 10, 10,
            10, false, 150, 50, 1, 5L);
    StringBuilder testBuilder = new StringBuilder();

    controller.processInput("-10", "10",
            "10", "false", "150", "50", "1");
    testBuilder.append("showErrorMessage");
    Assert.assertEquals(testBuilder.toString(), view.methodCalled.toString());
  }
}

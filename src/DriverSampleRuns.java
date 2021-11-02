import java.util.Scanner;

import dungeon.Direction;
import dungeon.Dungeon;
import dungeon.TreasureDungeon;

public class DriverSampleRuns {
  /**
   * Main method for the driver, starts the dungeon generation and game.
   */
  public static void main(String[] args) {
    System.out.println("Sample Run 1: Wrapping Dungeon");
    System.out.println("3 rows, 4 columns, 0 interconnectivity, 80 percent treasure.");
    Dungeon z = new TreasureDungeon(3,4,0,false, 80,4L);
    System.out.println("Player is at starting square when dungeon is created.");
    System.out.println("Player treasure starts empty: "+z.getPlayerTreasure());
    System.out.println("Current location treasure: "+z.getCurrentLocationTreasure());
    z.takeTreasure();
    System.out.println("Picked up treasure.");
    System.out.println("Player treasure: "+z.getPlayerTreasure()+". Location treasure: "+
            z.getCurrentLocationTreasure());
    System.out.println("Get Directions: "+ z.getDirections());
    System.out.println("Moving south");
    z.movePlayer(Direction.SOUTH);
    System.out.println("Current location treasure: "+z.getCurrentLocationTreasure());
    z.takeTreasure();
    System.out.println("Picked up treasure.");
    System.out.println("Player treasure: "+z.getPlayerTreasure()+". Location treasure: "+
            z.getCurrentLocationTreasure());
    System.out.println("Get Directions: "+ z.getDirections());
    System.out.println("Moving south");
    z.movePlayer(Direction.SOUTH);
    System.out.println("Get Directions: "+ z.getDirections());
    System.out.println("Moving east");
    z.movePlayer(Direction.EAST);
    System.out.println("Get Directions: "+ z.getDirections());
    System.out.println("Moving east");
    z.movePlayer(Direction.EAST);
    System.out.println("Get Directions: "+ z.getDirections());
    System.out.println("Moving west");
    z.movePlayer(Direction.WEST);
    System.out.println("Get Directions: "+ z.getDirections());
    System.out.println("Moving west");
    z.movePlayer(Direction.WEST);
    System.out.println("Get Directions: "+ z.getDirections());
    System.out.println("Moving north");
    z.movePlayer(Direction.NORTH);
    System.out.println("Get Directions: "+ z.getDirections());
    System.out.println("Moving east");
    z.movePlayer(Direction.EAST);
    System.out.println("Get Directions: "+ z.getDirections());
    System.out.println("Moving east");
    z.movePlayer(Direction.EAST);
    System.out.println("Get Directions: "+ z.getDirections());
    System.out.println("Moving west");
    z.movePlayer(Direction.WEST);
    System.out.println("Get Directions: "+ z.getDirections());
    System.out.println("Moving north");
    z.movePlayer(Direction.NORTH);
    System.out.println("Get Directions: "+ z.getDirections());
    System.out.println("Moving east");
    z.movePlayer(Direction.EAST);
    System.out.println("Get Directions: "+ z.getDirections());
    System.out.println("Moving east");
    z.movePlayer(Direction.EAST);
    System.out.println("Get Directions: "+ z.getDirections());
    System.out.println("Moving south");
    z.movePlayer(Direction.SOUTH);
    System.out.println("Get Directions: "+ z.getDirections());
    System.out.println("Moving south");
    z.movePlayer(Direction.SOUTH);
    System.out.println("The whole dungeon is traversed. If we try to pick up treasure or move we" +
            " will run into an error");
    try {
      System.out.println("Moving north");
      z.movePlayer(Direction.NORTH);
    } catch (IllegalArgumentException e) {
      System.out.println(e + " expected.");
    }
    try {
      System.out.println("Taking treasure");
      z.takeTreasure();
    } catch (IllegalArgumentException e) {
      System.out.println(e + " expected.");
    }
    System.out.println();
    System.out.println();
    System.out.println("Sample Run 2: Wrapping Dungeon");
    System.out.println("3 rows, 4 columns, 2 interconnectivity, 220 percent treasure.");
    Dungeon z2 = new TreasureDungeon(3,4,2,true, 220,4L);
    System.out.println("Player is at starting square when dungeon is created.");
    System.out.println("Player treasure starts empty: "+z2.getPlayerTreasure());
    System.out.println("Current location treasure: "+z2.getCurrentLocationTreasure());
    z2.takeTreasure();
    System.out.println("Picked up treasure.");
    System.out.println("Player treasure: "+z2.getPlayerTreasure()+". Location treasure: "+
            z2.getCurrentLocationTreasure());
    System.out.println("Get Directions: "+ z2.getDirections());
    System.out.println("Moving south");
    z2.movePlayer(Direction.SOUTH);
    System.out.println("Current location treasure: "+z2.getCurrentLocationTreasure());
    z2.takeTreasure();
    System.out.println("Picked up treasure.");
    System.out.println("Player treasure: "+z2.getPlayerTreasure()+". Location treasure: "+
            z2.getCurrentLocationTreasure());
    System.out.println("Get Directions: "+ z2.getDirections());
    System.out.println("Moving east");
    z2.movePlayer(Direction.EAST);
    System.out.println("Get Directions: "+ z2.getDirections());
    System.out.println("Moving north");
    z2.movePlayer(Direction.NORTH);
    System.out.println("Get Directions: "+ z2.getDirections());
    System.out.println("Moving south");
    z2.movePlayer(Direction.SOUTH);
    System.out.println("Get Directions: "+ z2.getDirections());
    System.out.println("Moving east");
    z2.movePlayer(Direction.EAST);
    System.out.println("Get Directions: "+ z2.getDirections());
    System.out.println("Moving north");
    z2.movePlayer(Direction.NORTH);
    System.out.println("Get Directions: "+ z2.getDirections());
    System.out.println("Moving south");
    z2.movePlayer(Direction.SOUTH);
    System.out.println("Get Directions: "+ z2.getDirections());
    System.out.println("Moving west");
    z2.movePlayer(Direction.WEST);
    System.out.println("Get Directions: "+ z2.getDirections());
    System.out.println("Moving west");
    z2.movePlayer(Direction.WEST);
    System.out.println("Get Directions: "+ z2.getDirections());
    System.out.println("Moving west");
    z2.movePlayer(Direction.WEST);
    System.out.println("Get Directions: "+ z2.getDirections());
    System.out.println("Moving north");
    z2.movePlayer(Direction.NORTH);
    System.out.println("Get Directions: "+ z2.getDirections());
    System.out.println("Moving north");
    z2.movePlayer(Direction.NORTH);
    System.out.println("The whole dungeon is traversed. If we try to pick up treasure or move we" +
            " will run into an error");
    try {
      System.out.println("Moving north");
      z.movePlayer(Direction.NORTH);
    } catch (IllegalArgumentException e) {
      System.out.println(e + " expected.");
    }
    try {
      System.out.println("Taking treasure");
      z.takeTreasure();
    } catch (IllegalArgumentException e) {
      System.out.println(e + " expected.");
    }
  }
}

import java.util.Scanner;

import dungeon.Dungeon;
import dungeon.TreasureDungeon;

public class Driver {
  /**
   * Main method for the driver, starts the dungeon generation and game.
   */
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String input = "";
    int rows;
    int columns;
    int interconnectivity;
    int t_percent;
    boolean wrapped;
    System.out.println("Create a dungeon! \n");
    System.out.println("Specify number of rows: \n");
    rows = Integer.parseInt(scan.nextLine());
    System.out.println("Specify number of columns: \n");
    columns = Integer.parseInt(scan.nextLine());
    System.out.println("Specify interconnectivity: \n");
    interconnectivity = Integer.parseInt(scan.nextLine());
    System.out.println("dungeon.Treasure percent: \n");
    t_percent = Integer.parseInt(scan.nextLine());
    Dungeon d = new TreasureDungeon(rows, columns, interconnectivity, false, t_percent);
    System.out.println("'d' for directions, 'north' to move north, 'q' to quit");

    while (!input.equalsIgnoreCase("no") && !input.equalsIgnoreCase("q")) {
      if (input.equalsIgnoreCase("yes")) {
        System.out.println("Battle starting! \n");
      }
    }


//    while (!input.equalsIgnoreCase("q")) { // TODO change to hitting end spot
//      if (input.equalsIgnoreCase("d")) {
//        System.out.println("D");
//        //System.out.println(d.getDirections());
//
//      }
//      if (input.equalsIgnoreCase("n")) {
//        d.movePlayer(dungeon.Direction.NORTH);
//      }
//      if (input.equalsIgnoreCase("s")) {
//        d.movePlayer(dungeon.Direction.SOUTH);
//      }
//      if (input.equalsIgnoreCase("e")) {
//        d.movePlayer(dungeon.Direction.EAST);
//      }
//      if (input.equalsIgnoreCase("w")) {
//        d.movePlayer(dungeon.Direction.WEST);
//      }
//    }
  }
}


//      System.out.println("Create a dungeon! \n");
//      try {
//        System.out.println("Specify number of rows: \n");
//        int rows = Integer.parseInt(scan.nextLine());
//        break;
//      } catch (NumberFormatException nfe) {
//        System.out.print("Invalid number, try again: ");
//      }
//      try {
//        System.out.println("Specify number of columns: \n");
//        columns = Integer.parseInt(scan.nextLine());
//        break;
//      } catch (NumberFormatException nfe) {
//        System.out.print("Invalid number, try again: ");
//      }
//      try {
//        System.out.println("Specify interconnectivity: \n");
//        interconnectivity = Integer.parseInt(scan.nextLine());
//        break;
//      } catch (NumberFormatException nfe) {
//        System.out.print("Invalid number, try again: ");
//      }
//      dungeon.Dungeon d = new dungeon.TreasureDungeon(rows, columns, interconnectivity, true);


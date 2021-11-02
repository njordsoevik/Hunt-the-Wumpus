import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import dungeon.Direction;
import dungeon.Dungeon;
import dungeon.TreasureDungeon;

/**
 * Driver for the command-line sample runs.
 */
public class Driver {

  /**
   * Main method for the driver, starts the dungeon input generation and game.
   */
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();
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
    System.out.println("Specify wrapped (T/F): \n");
    String temp = scan.nextLine();
    if (temp.equalsIgnoreCase("t")) {
      wrapped = true;
    } else if (temp.equalsIgnoreCase("f")) {
      wrapped = false;
    } else {
      throw new IllegalArgumentException("Not a valid input!");
    }
    System.out.println("Specify interconnectivity: \n");
    interconnectivity = Integer.parseInt(scan.nextLine());
    System.out.println("dungeon.Treasure percent: \n");
    t_percent = Integer.parseInt(scan.nextLine());
    Dungeon d = new TreasureDungeon(rows, columns, interconnectivity, wrapped, t_percent);
    while (!d.isGameOver()) {
      d.takeTreasure();
      System.out.println("Picking up any treasure.");
      System.out.println("Player treasure: " + d.getPlayerTreasure());
      Set<Direction> ds = d.getDirections();
      Direction dir = null;
      System.out.println("Get Directions: " + ds);
      int directionIndex = rand.nextInt(ds.size());
      int item = 0;
      for (Direction iterator : ds) {
        if (item == directionIndex) {
          dir = iterator;
          break;
        }
        item++;
      }
      System.out.println("Moving: " + dir);
      d.movePlayer(dir);
    }
    System.out.println("End of dungeon reached!");
  }
}

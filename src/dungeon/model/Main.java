package dungeon.model;

import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

import dungeon.controller.DungeonConsoleController;

/**
 * Driver for the command-line sample runs.
 */
public class Main {
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
    int a_percent;
    int n_monsters;
    boolean wrapped;
    System.out.println("Create a dungeon! \n");
    System.out.println("Specify number of rows:");
    rows = Integer.parseInt(scan.nextLine());
    System.out.println("Specify number of columns:");
    columns = Integer.parseInt(scan.nextLine());
    System.out.println("Specify wrapped (T/F):");
    String temp = scan.nextLine();
    if (temp.equalsIgnoreCase("t")) {
      wrapped = true;
    } else if (temp.equalsIgnoreCase("f")) {
      wrapped = false;
    } else {
      throw new IllegalArgumentException("Not a valid input!");
    }
    System.out.println("Specify interconnectivity:");
    interconnectivity = Integer.parseInt(scan.nextLine());
    System.out.println("Treasure percent:");
    t_percent = Integer.parseInt(scan.nextLine());
    System.out.println("Arrow percent:");
    a_percent = Integer.parseInt(scan.nextLine());
    System.out.println("Number of Otyughs:");
    n_monsters = Integer.parseInt(scan.nextLine());
    OtyughDungeon d = new OtyughTreasureDungeon(rows, columns, interconnectivity, wrapped, t_percent, a_percent, n_monsters);
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    System.out.println("Input will be from System.in and the output will be to System.out. \n");
    new DungeonConsoleController(input, output).playGame(d);
  }
}

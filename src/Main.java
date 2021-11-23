import java.io.InputStreamReader;
import java.util.Scanner;

import dungeon.controller.DungeonConsoleController;
import dungeon.model.OtyughDungeon;
import dungeon.model.OtyughTreasureDungeon;

/**
 * Driver for the command-line sample runs.
 */
public class Main {

  /**
   * Main method for the driver, starts the dungeon input generation and game.
   * @param args List of arguments:
   *             1. Rows.
   *             2. Columns.
   *             3. Wrapped
   *             4. Interconnectivity.
   *             5. Treasure Percent.
   *             6. Arrow Percent.
   *             7. Number of Otyughs.
   */
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int rows;
    int columns;
    int interconnectivity;
    int t_percent;
    int a_percent;
    int n_monsters;
    boolean wrapped;
    OtyughDungeon d;
    if (args != null) {
      rows = Integer.parseInt(args[0]);
      columns = Integer.parseInt(args[1]);
      wrapped = Boolean.parseBoolean(args[2]);
      interconnectivity = Integer.parseInt(args[3]);
      t_percent = Integer.parseInt(args[4]);
      a_percent = Integer.parseInt(args[5]);
      n_monsters = Integer.parseInt(args[6]);
    } else {
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
    }
    d = new OtyughTreasureDungeon(rows, columns, interconnectivity, wrapped
            , t_percent, a_percent, n_monsters);
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    System.out.println("Input will be from System.in and the output will be to System.out. \n");
    new DungeonConsoleController(input, output).playGame(d);
  }
}

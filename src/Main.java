import java.io.InputStreamReader;
import java.util.Scanner;

import dungeon.controller.DungeonConsoleController;
import dungeon.controller.DungeonViewController;
import dungeon.controller.DungeonViewControllerImpl;
import dungeon.model.OtyughDungeon;
import dungeon.model.OtyughTreasureDungeon;
import dungeon.view.DungeonSwingView;
import dungeon.view.DungeonView;

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
      d = new OtyughTreasureDungeon(rows, columns, interconnectivity, wrapped
              , t_percent, a_percent, n_monsters);
      Readable input = new InputStreamReader(System.in);
      Appendable output = System.out;
      System.out.println("Input will be from System.in and the output will be to System.out. \n");
      new DungeonConsoleController(input, output).playGame(d);
    } else {
      DungeonView view = new DungeonSwingView();
      DungeonViewController controller = new DungeonViewControllerImpl();
      controller.setView(view);
    }

  }
}

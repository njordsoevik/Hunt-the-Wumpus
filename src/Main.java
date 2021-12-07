import java.io.InputStreamReader;
import java.util.Random;

import dungeon.controller.DungeonConsoleController;
import dungeon.controller.DungeonController;
import dungeon.controller.DungeonViewController;
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
   *
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
    int rows;
    int columns;
    int interconnectivity;
    int t_percent;
    int a_percent;
    int n_monsters;
    boolean wrapped;
    OtyughDungeon model;
    if (args.length > 0) {
      rows = Integer.parseInt(args[0]);
      columns = Integer.parseInt(args[1]);
      wrapped = Boolean.parseBoolean(args[2]);
      interconnectivity = Integer.parseInt(args[3]);
      t_percent = Integer.parseInt(args[4]);
      a_percent = Integer.parseInt(args[5]);
      n_monsters = Integer.parseInt(args[6]);
      model = new OtyughTreasureDungeon(rows, columns, interconnectivity, wrapped
              , t_percent, a_percent, n_monsters);
      Readable input = new InputStreamReader(System.in);
      Appendable output = System.out;
      System.out.println("Input will be from System.in and the output will be to System.out. \n");
      DungeonController controller = new DungeonConsoleController(model, input, output);
      controller.go();
    } else {
      Random rand = new Random();
      Long seed = rand.nextLong();
      model = new OtyughTreasureDungeon(5, 5, 0, false, 150, 50, 1, seed);
      DungeonView view = new DungeonSwingView(model);
      DungeonController controller = new DungeonViewController(model, view, 5, 5, 0, false, 150, 50, 1, seed);
      controller.go();
    }

  }
}
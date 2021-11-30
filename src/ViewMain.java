import dungeon.controller.DungeonViewController;
import dungeon.controller.DungeonViewControllerImpl;
import dungeon.model.OtyughDungeon;
import dungeon.model.OtyughTreasureDungeon;
import dungeon.model.ROtyughDungeon;
import dungeon.view.DungeonSwingView;
import dungeon.view.DungeonView;

/**
 * Driver for the view runs.
 */
public class ViewMain {

  /**
   * Main method for the view driver, starts the dungeon input generation and game.
   */
  public static void main(String[] args) {
    OtyughDungeon model = new OtyughTreasureDungeon(3, 4, 0, false, 20, 100, 1);
    DungeonView view = new DungeonSwingView((ROtyughDungeon) model);
    DungeonViewController controller = new DungeonViewControllerImpl(model);
    controller.setView(view);
  }
}

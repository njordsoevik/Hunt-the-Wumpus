import dungeon.controller.DungeonViewController;
import dungeon.controller.DungeonViewControllerImpl;
import dungeon.model.OtyughDungeon;
import dungeon.model.OtyughTreasureDungeon;
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
    DungeonView view = new DungeonSwingView();
    DungeonViewController controller = new DungeonViewControllerImpl(view);
    controller.createMenu();
  }
}

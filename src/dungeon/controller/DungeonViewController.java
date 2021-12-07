package dungeon.controller;

import java.util.Random;

import dungeon.model.Direction;
import dungeon.model.OtyughDungeon;
import dungeon.model.OtyughTreasureDungeon;
import dungeon.view.DungeonView;

/**
 * Allows players to interact with the dungeon through the graphical user interface. Dungeon is
 * generated through graphical user interface input fields.
 */
public class DungeonViewController implements DungeonController, Features {
  private final DungeonView view;
  private OtyughDungeon model;
  private Long seed;
  private int rowsParse;
  private int colsParse;
  private int connectivityParse;
  private boolean wrappedParse;
  private int treasureParse;
  private int arrowsParse;
  private int monstersParse;

  /**
   * Constructor for the view controller.
   *
   * @param m the model to pass information to.
   * @param v the view to make updates to.
   */
  public DungeonViewController(OtyughDungeon m, DungeonView v, int rows, int columns,
                               int connectivity, boolean wrapped, int treasure, int arrows,
                               int monsters, Long seed) {
    this.model = m;
    this.view = v;
    this.seed = seed;
    rowsParse = rows;
    colsParse = columns;
    connectivityParse = connectivity;
    wrappedParse = wrapped;
    treasureParse = treasure;
    arrowsParse = arrows;
    monstersParse = monsters;
  }

  @Override
  public void go() {
    view.setFeatures(this);
    view.makeVisible();
    view.resetFocus();
  }

  @Override
  public void processInput(String rows, String columns, String connectivity, String wrapped,
                           String treasure, String arrows, String monsters) {
    Random rand = new Random();
    Long backupSeed = seed.longValue();
    try {
      rowsParse = Integer.parseInt(rows);
      colsParse = Integer.parseInt(columns);
      connectivityParse = Integer.parseInt(connectivity);
      wrappedParse = Boolean.parseBoolean(wrapped);
      treasureParse = Integer.parseInt(treasure);
      arrowsParse = Integer.parseInt(arrows);
      monstersParse = Integer.parseInt(monsters);
      seed = rand.nextLong();
      passInput(rowsParse, colsParse, connectivityParse, wrappedParse, treasureParse, arrowsParse
              , monstersParse, seed);
    } catch (IllegalArgumentException ex) {
      this.seed = backupSeed;
      view.showErrorMessage(ex.toString());
    }
  }

  private void passInput(int rows, int columns, int connectivity, boolean wrapped,
                         int treasure, int arrows, int monsters, Long seed) {
    Long backupSeed = seed.longValue();
    try {
      model = new OtyughTreasureDungeon(rows, columns, connectivity, wrapped, treasure, arrows,
              monsters, seed);
      view.updateModel(rows, columns, model);
      updateView();
    } catch (IllegalArgumentException ex) {
      this.seed = backupSeed;
      view.showErrorMessage(ex.toString());
    }
  }

  @Override
  public void shootArrow(Direction direction, int distance) {
    try {
      execute(new Shoot(direction, distance));
      updateView();
    } catch (IllegalArgumentException ex) {
      view.showErrorMessage(ex.toString());
    }
  }

  @Override
  public void move(Direction d) {
    try {
      execute(new Move(d));
      updateView();
    } catch (IllegalArgumentException ex) {
      view.showErrorMessage(ex.toString());
    }
  }

  @Override
  public void pickUp() {
    try {
      execute(new PickUp());
      updateView();
    } catch (IllegalArgumentException ex) {
      view.showErrorMessage(ex.toString());
    }
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void restartProgram() {
    passInput(rowsParse, colsParse, connectivityParse, wrappedParse, treasureParse, arrowsParse,
            monstersParse, seed);
  }

  private void execute(OtyughDungeonCommand cmd) {
    cmd.execute(model);
  }

  private void updateView() {
    view.refresh();
    view.resetFocus();
  }
}

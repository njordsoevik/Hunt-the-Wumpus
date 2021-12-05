package dungeon.controller;

/**
 * Represents a Controller for Dungeon and handles user moves by executing them using the model.
 * Convey move outcomes to the user in some form.
 */
public interface DungeonController {

  /**
   * Execute a single game of the dungeon given a dungeon Model. When the game is over,
   * the playGame method ends.
   */
  void go();
}

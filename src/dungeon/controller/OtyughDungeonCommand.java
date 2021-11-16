package dungeon.controller;

import dungeon.model.OtyughDungeon;

/**
 * Represents commands for the Dungeon model to be executed by the controller.
 */
interface OtyughDungeonCommand {

  /**
   * Execute a single command for the dungeon controller.
   *
   * @param m a non-null dungeon Model.
   */
  void execute(OtyughDungeon m);
}

package dungeon.controller;

import dungeon.model.OtyughDungeon;

/**
 * Command for the player to pick up treasures or arrows.
 */
class PickUp implements OtyughDungeonCommand {

  @Override
  public void execute(OtyughDungeon m) {
    m.takeArrows();
    m.takeTreasure();
  }
}

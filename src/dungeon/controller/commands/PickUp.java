package dungeon.controller.commands;

import dungeon.Direction;
import dungeon.OtyughDungeon;
import dungeon.controller.OtyughDungeonCommand;

public class PickUp implements OtyughDungeonCommand {

  @Override
  public void execute(OtyughDungeon m) {
    m.takeArrows();
    m.takeTreasure();
  }
}

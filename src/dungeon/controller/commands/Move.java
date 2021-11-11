package dungeon.controller.commands;

import java.util.Locale;

import dungeon.Direction;
import dungeon.OtyughDungeon;
import dungeon.controller.OtyughDungeonCommand;

public class Move implements OtyughDungeonCommand {
  Direction d;

  public Move(Direction d) {
    this.d = d;
  }

  @Override
  public void execute(OtyughDungeon m) {
    m.movePlayer(d);
  }

}

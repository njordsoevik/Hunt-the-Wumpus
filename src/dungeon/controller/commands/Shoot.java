package dungeon.controller.commands;

import java.util.Locale;

import dungeon.Direction;
import dungeon.OtyughDungeon;
import dungeon.controller.OtyughDungeonCommand;

public class Shoot implements OtyughDungeonCommand {
  Direction d;
  int x;

  public Shoot(Direction d, int x) {
    this.d = d;
    this.x = x;
  }

  @Override
  public void execute(OtyughDungeon m) {
    m.shootArrow(d,x);
  }

}

package dungeon.controller;

import java.util.Locale;

import dungeon.Direction;
import dungeon.OtyughDungeon;

public class Move implements OtyughDungeonCommand{
  String d;

  public Move(String d) {
    this.d = d;
  }

  @Override
  public void execute(OtyughDungeon m) {
    Direction dir = Direction.valueOf(d.toUpperCase());
    m.movePlayer(dir);
  }

}

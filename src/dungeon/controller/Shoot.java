package dungeon.controller;

import dungeon.model.Direction;
import dungeon.model.OtyughDungeon;

/**
 * Command for the player to shoot their bow a direction and distance.
 */
class Shoot implements OtyughDungeonCommand {
  Direction d;
  int x;

  /**
   * Constructor for the shoot command.
   *
   * @param d the direction to shoot
   * @param x the distance to shoot
   */
  public Shoot(Direction d, int x) {
    this.d = d;
    this.x = x;
  }

  @Override
  public void execute(OtyughDungeon m) {
    m.shootArrow(d, x);
  }

}

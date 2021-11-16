package dungeon.controller;

import dungeon.model.Direction;
import dungeon.model.OtyughDungeon;

/**
 * Command for the player to move a specified direction.
 */
public class Move implements OtyughDungeonCommand {
  Direction d;

  /**
   * Constructor for the move command.
   *
   * @param d the direction to move
   */
  public Move(Direction d) {
    this.d = d;
  }

  @Override
  public void execute(OtyughDungeon m) {
    m.movePlayer(d);
  }

}

package dungeon.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import dungeon.Direction;
import dungeon.OtyughDungeon;

/**
 * This starter files is for students to implement a console controller for the
 * TicTacToe MVC assignment.
 */
public class DungeonConsoleController implements DungeonController {

  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public DungeonConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(OtyughDungeon m) {
    m.printGrid();
    String element = "";
    OtyughDungeonCommand cmd = null;
    if (!(m instanceof OtyughDungeon)) {
      throw new IllegalArgumentException("Model is invalid!");
    }
    while (scan.hasNext()) {
      try {
        //out.append("You are in a ").append(m.getCurrentLocationType().toString()).append("\n");
        out.append("Doors lead to:");
        Iterator<Direction> itr = m.getDirections().iterator();
        while (itr.hasNext()) {
          out.append(" ").append(itr.next().toString());
        }
        out.append("\n");
        out.append("Move, Pickup, or Shoot? (M-P-S) ").append("\n");
        element = scan.next();
        switch (element) {
          case "q":
          case "Q":
            try {
              out.append("Game quit! Ending game state.").append("\n");
              return;
            } catch (IOException ioe) {
              throw new IllegalStateException("Append failed", ioe);
            }
          case "m":
          case "M":
            try {
              out.append("Moving, choose direction: ").append("\n");
            } catch (IOException ioe) {
              throw new IllegalStateException("Append failed", ioe);
            }
            cmd = new Move(scan.next());
            break;
          default:
            out.append("Unknown operator: ").append(element).append("\n");
            break;
        }
        if (cmd!=null) {
          cmd.execute(m);
        }
        //out.append("You are in a ").append(m.getCurrentLocationType().toString()).append("\n");
        out.append("Doors lead to:");
        itr = m.getDirections().iterator();
        while (itr.hasNext()) {
          out.append(" ").append(itr.next().toString());
        }
        out.append("\n");
        out.append("Move, Pickup, or Shoot? (M-P-S) ").append("\n");
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }

    }
  }
}

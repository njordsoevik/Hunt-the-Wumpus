package dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    if (!(m instanceof OtyughDungeon)) {
      throw new IllegalArgumentException("Model is invalid!");
    }
    String element = "";
    Boolean needMove;
    List<Integer> moves = new ArrayList();

    try {
      out.append(m.toString()).append("\n"); // Checked
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
    while (scan.hasNext()) {

    }
  }
}

package dungeon.controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import dungeon.Arrow;
import dungeon.Direction;
import dungeon.Health;
import dungeon.OtyughDungeon;
import dungeon.Treasure;
import dungeon.controller.commands.Move;
import dungeon.controller.commands.PickUp;
import dungeon.controller.commands.Shoot;

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
    String element = "";
    String move = "";
    Direction moveDirecton;
    int distance;
    OtyughDungeonCommand cmd = null;

    if (!(m instanceof OtyughDungeon)) {
      throw new IllegalArgumentException("Model is invalid!");
    }
    helperPrint(m);
    while (scan.hasNext()) {
      try {
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
            out.append("Choose direction: ").append("\n");
            while (true) {
              try {
                move = scan.next();
                moveDirecton = Direction.valueOf(move.toUpperCase());
                break;
              } catch (IllegalArgumentException ex) {
                out.append("Invalid move, try again.").append("\n");
              }
            }
            out.append("Moving ").append(move).append("\n");
            cmd = new Move(moveDirecton);
            break;
          case "p":
          case "P":
            out.append("Picking up any treasures or arrows").append("\n");
            cmd = new PickUp();
            break;
          case "s":
          case "S":
            out.append("Choose direction: ").append("\n");
            while (true) {
              try {
                move = scan.next();
                moveDirecton = Direction.valueOf(move.toUpperCase());
                break;
              } catch (IllegalArgumentException ex) {
                out.append("Invalid direction, try again.").append("\n");
              }
            }
            out.append("Choose distance: ").append("\n");
            while (true) {
              try {
                distance = scan.nextInt();
                break;
              } catch (InputMismatchException ex) {
                out.append("Invalid distance, try again.").append("\n");
              }
            }
            out.append("Shooting ").append(move).append(" ").append(distance + " ").append("squares").append("\n");
            cmd = new Shoot(moveDirecton,distance);
            break;
          default:
            out.append("Unknown operator: ").append(element).append("\n");
            break;
        }
        if (cmd != null) {
          cmd.execute(m);
        }
        helperPrint(m);
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }

      if (m.isGameOver()) {
        try {
          if (m.getPlayerHealth() == Health.DEAD) {
            out.append("An Otyugh has eaten you! Game over.");
          } else {
            out.append("End square reached, game over!");
          }
        }
         catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
      }

    }
  }

  private void helperPrint(OtyughDungeon m) {
    Iterator<Direction> itr;
    try {
      out.append("\n");
      // Show cave or tunnel
      //out.append("You are in a ").append(m.getCurrentLocationType().toString()).append("\n");

      // Show directions
      out.append("Doors lead to:");
      itr = m.getDirections().iterator();
      while (itr.hasNext()) {
        out.append(" ").append(itr.next().toString());
      }
      out.append("\n");

      // Show treasures and arrows
      out.append("The cave you are in holds arrows: ");
      for (Arrow a : m.getCurrentLocationArrows()) {
        out.append(" ").append(a.toString());
      }
      out.append("\n");
      out.append("The cave you are in holds treasures: ");
      for (Treasure d : m.getCurrentLocationTreasure()) {
        out.append(" ").append(d.toString());
      }
      out.append("\n");

      // TODO Show smell

      //MPS question
      out.append("\n");
      out.append("Move, Pickup, or Shoot? (M-P-S) ").append("\n");
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }
}

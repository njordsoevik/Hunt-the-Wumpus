package dungeon.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import dungeon.model.Arrow;
import dungeon.model.Direction;
import dungeon.model.Health;
import dungeon.model.OtyughDungeon;
import dungeon.model.Smell;
import dungeon.model.Treasure;

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
    Direction moveDirection = null;
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
            out.append("Game quit! Ending game state.").append("\n");
            return;
          case "i":
          case "I":
            List<Arrow> arrows = m.getPlayerArrows();
            if (!arrows.isEmpty()) {
              out.append("You are holding arrows: ");
              for (Arrow a : arrows) {
                out.append(" ").append(a.toString());
              }
              out.append("\n");
            } else {
              out.append("You are holding no arrows").append("\n");
            }

            List<Treasure> treasures = m.getPlayerTreasure();
            if (!treasures.isEmpty()) {
              out.append("You are holding treasures: ");
              for (Treasure d : treasures) {
                out.append(" ").append(d.toString());
              }
              out.append("\n");
            } else {
              out.append("You are holding no treasure").append("\n");
            }
            break;
          case "m":
          case "M":
            out.append("Choose direction: ").append("\n");
            move = scan.next();
            switch (move) {
              case "q":
              case "Q":
                out.append("Game quit! Ending game state.").append("\n");
                return;
              case "e":
              case "east":
              case "E":
              case "EAST":
                moveDirection = Direction.EAST;
                break;
              case "w":
              case "west":
              case "W":
              case "WEST":
                moveDirection = Direction.WEST;
                break;
              case "n":
              case "north":
              case "N":
              case "NORTH":
                moveDirection = Direction.NORTH;
                break;
              case "s":
              case "south":
              case "S":
              case "SOUTH":
                moveDirection = Direction.SOUTH;
                break;
              default: // No default case needed
            }
            out.append("Moving ").append(move).append("\n");
            if (m.getDirections().contains(moveDirection)) {
              cmd = new Move(moveDirection);
            } else {
              out.append("Cannot move ").append(move).append(" from this location.");
            }
            break;
          case "p":
          case "P":
            out.append("You have picked up any treasures or arrows").append("\n");
            cmd = new PickUp();
            break;
          case "s":
          case "S":
            out.append("Choose direction: ").append("\n");
            move = scan.next();
            switch (move) {
              case "q":
              case "Q":
                out.append("Game quit! Ending game state.").append("\n");
                return;
              case "e":
              case "east":
              case "E":
              case "EAST":
                moveDirection = Direction.EAST;
                break;
              case "w":
              case "west":
              case "W":
              case "WEST":
                moveDirection = Direction.WEST;
                break;
              case "n":
              case "north":
              case "N":
              case "NORTH":
                moveDirection = Direction.NORTH;
                break;
              case "s":
              case "south":
              case "S":
              case "SOUTH":
                moveDirection = Direction.SOUTH;
                break;
              default: // No default case needed
            }
            out.append("Choose distance: ").append("\n");
            while (true) {
              try {
                distance = Integer.parseInt(scan.next());
                break;
              } catch (NumberFormatException ex) {
                out.append("Invalid distance, try again.").append("\n");
              }
            }
            if (Arrays.asList(Direction.values()).contains(moveDirection) && distance >= 0) {
              out.append("Shooting ").append(moveDirection.toString()).append(" ")
                      .append(distance + " ").append("squares").append("\n");
              cmd = new Shoot(moveDirection, distance);
            } else {
              out.append("Cannot shoot in ").append(move).append(" direction ")
                      .append(distance + " units");
            }
            break;
          default:
            out.append("Unknown operator: ").append(element).append("\n");
            break;
        }
        if (cmd != null) {
          cmd.execute(m);
        }
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
          return;
        } catch (IOException ioe) {
          throw new IllegalStateException("Append failed", ioe);
        }
      }
      helperPrint(m);
    }
  }

  private void helperPrint(OtyughDungeon m) {
    Iterator<Direction> itr;
    try {
      out.append("\n").append("\n");
      // Show directions
      out.append("Doors lead to:");
      itr = m.getDirections().iterator();
      while (itr.hasNext()) {
        out.append(" ").append(itr.next().toString());
      }
      out.append("\n");

      // Show treasures and arrows
      List<Arrow> arrows = m.getCurrentLocationArrows();
      if (!arrows.isEmpty()) {
        out.append("The cave you are in holds arrows: ");
        for (Arrow a : arrows) {
          out.append(" ").append(a.toString());
        }
        out.append("\n");
      }

      List<Treasure> treasures = m.getCurrentLocationTreasure();
      if (!treasures.isEmpty()) {
        out.append("The cave you are in holds treasures:");
        for (Treasure d : treasures) {
          out.append(" ").append(d.toString());
        }
        out.append("\n");
      }

      // Show smell
      if (m.getSmell() == Smell.MORE_PUNGENT) {
        out.append("There is a VERY pungent smell in the air").append("\n");
      } else if (m.getSmell() == Smell.LESS_PUNGENT) {
        out.append("There is a faint smell in the air").append("\n");
      } else {
        out.append("There is no smell in the air").append("\n");
      }

      // MPS question
      out.append("\n").append("Move, Pickup, Shoot, or Player Information? (M-P-S-I) ").append("\n");
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }
}

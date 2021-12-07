package dungeon.controller;

import dungeon.model.Direction;

/**
 * This interface represents a set of features that the program offers. Each
 * feature is exposed as a function in this interface. This function is used
 * suitably as a callback by the view, to pass control to the controller. How
 * the view uses them as callbacks is completely up to how the view is designed
 * (e.g. it could use them as a callback for a button, or a callback for a
 * dialog box, or a set of text inputs, etc.)
 */

public interface Features {
  /**
   * Process the inputs entered by the user.
   *
   * @param rows              The number of rows in the dungeon.
   * @param columns           The number of columns in the dungeon.
   * @param connectivity The interconnectivity for kruskal's algorithm.
   * @param wrapped           If the dungeon should wrap around the edges.
   * @param treasure   Percent of caves with treasure.
   * @param arrows      Percent of caves with arrows.
   * @param monsters      The number of Otyughs to inhabit the caves. If zero is inputted, one
   *                          Otyugh will be placed at the final cave.
   */
  void processInput(String rows, String columns, String connectivity, String wrapped
          , String treasure, String arrows, String monsters);

  /**
   * Shoot an arrow.
   */
  void shootArrow(Direction direction, int distance);

  /**
   * Move in a direction.
   */
  void move(Direction d);

  /**
   * Pick up any items.
   */
  void pickUp();

  /**
   * Exit the program.
   */
  void exitProgram();

  /**
   * Restart the program.
   */
  void restartProgram();
}

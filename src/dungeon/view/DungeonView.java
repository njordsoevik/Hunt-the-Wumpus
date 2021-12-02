package dungeon.view;

import java.util.Set;

import dungeon.controller.Features;
import dungeon.model.Direction;
import dungeon.model.RDungeon;

/**
 * A view for TicTacToe: display the game board and provide visual interface
 * for users.
 */
public interface DungeonView {

  /**
   * Reset the focus on the appropriate part of the view that has the keyboard
   * listener attached to it, so that keyboard events will still flow through.
   */
  void resetFocus();

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();

  /**
   * Get the set of feature callbacks that the view can use.
   *
   * @param f the set of feature callbacks as a Features object
   */
  void setFeatures(Features f);

  /**
   * Transmit an error message to the view, in case
   * the command could not be processed correctly
   * @param error
   */
  void showErrorMessage(String error);

  void setNewDungeon(int x, int y, RDungeon model);

}

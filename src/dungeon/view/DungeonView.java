package dungeon.view;

import java.awt.event.ActionListener;

import dungeon.controller.DungeonViewController;
import dungeon.controller.Features;

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
}

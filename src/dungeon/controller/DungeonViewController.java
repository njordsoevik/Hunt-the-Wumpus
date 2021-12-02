package dungeon.controller;

import dungeon.view.DungeonView;

public interface DungeonViewController {
  /**
   * Handle an action in a single cell of the board, such as to make a move.
   *
   * @param row the row of the clicked cell
   * @param col the column of the clicked cell
   */
  void handleCellClick(int row, int col);

  /**
   * Start the program, i.e. give control to the controller
   */
  void go();
}

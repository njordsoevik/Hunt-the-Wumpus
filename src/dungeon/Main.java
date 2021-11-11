package dungeon;

import java.io.InputStreamReader;

import dungeon.controller.DungeonConsoleController;

/**
 * Run a TicTacToe game interactively on the console.
 */
public class Main {
  /**
   * Run a TicTacToe game interactively on the console.
   */
  public static void main(String[] args) {
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    new DungeonConsoleController(input, output).playGame(new OtyughTreasureDungeon(3, 4, 0, false, 20, 1000, 0, 5L));
  }
}

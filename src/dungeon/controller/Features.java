package dungeon.controller;

/**
 * This interface represents a set of features that the program offers. Each
 * feature is exposed as a function in this interface. This function is used
 * suitably as a callback by the view, to pass control to the controller. How
 * the view uses them as callbacks is completely up to how the view is designed
 * (e.g. it could use them as a callback for a button, or a callback for a
 * dialog box, or a set of text inputs, etc.)
 *
 * <p>Each function is designed to take in the necessary data to complete that
 * functionality.
 */

public interface Features {
  /**
   * Process the inputs entered by the user.
   * 
   * @param
   */
  void processInput(String rows, String columns, String connectivity, String wrapped
          , String treasure, String arrows, String monsters);

  /**
   * Exit the program.
   */
  void exitProgram();
}

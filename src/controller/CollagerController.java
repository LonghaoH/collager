package controller;

import java.io.IOException;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This interface represents a controller for a collager. Allows for different styles of running,
 * one inputting the commands via System.in,
 * one with a provided script in a .txt file, and the standard GUI view where input is taken
 * from the GUI.
 */
public interface CollagerController {

  /**
   * Allows the user to run the controller. It accepts inputs from the user to perform
   * specific actions.
   *
   * @throws IOException if the inputted command is not supported by the controller,
   *                     if the file is not found, or if the file already exist.
   */
  void run() throws IOException;

  /**
   * Allows the user to run the controller given a script file.
   *
   * @param filePath the file path of the script file, of txt format.
   * @throws IOException if the inputted command is not supported by the controller,
   *                     if the file is not found, or if the file already exist.
   */
  void runScriptMode(String filePath) throws IOException, UnsupportedLookAndFeelException,
          ClassNotFoundException, InstantiationException, IllegalAccessException;

  /**
   * Allows the user to run the controller via the GUI view.
   *
   * @throws IOException if the inputted command is not supported by the controller,
   *                     if the file is not found, or if the file already exist.
   */
  void runGUIMode() throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException,
          InstantiationException, IllegalAccessException;
}

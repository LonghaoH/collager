package controller;

import java.io.IOException;

/**
 * This interface represents a controller for a collager.
 */
public interface CollagerController {

  /**
   * Allows the user to run the controller. It accepts text-based commands to perform
   * specific actions.
   *
   * @throws IOException if the inputted command is not supported by the controller,
   *                     if the file is not found, or if the file already exist.
   */
  void run() throws IOException;
}

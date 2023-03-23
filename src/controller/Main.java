package controller;

import java.io.IOException;

/**
 * A class to run CollagerPPM.
 */
public class Main {

  /**
   * Runs the collager program.
   *
   * @param args arguments
   */
  public static void main(String[] args) {

    try {
      new CollagerControllerImpl(System.out).run();
    } catch (IOException e) {
      throw new RuntimeException();
    }

  }
}

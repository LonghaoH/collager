package controller;

import java.io.IOException;
import java.io.InputStreamReader;

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
      new CollagerControllerImpl(System.out, new InputStreamReader(System.in)).run();
    } catch (IOException e) {
      throw new RuntimeException();
    }

  }
}

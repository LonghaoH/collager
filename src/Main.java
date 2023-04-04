import java.io.IOException;
import java.io.InputStreamReader;

import controller.CollagerControllerImpl;
import model.AbstractCollager;
import model.CollagerPPM;

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

    AbstractCollager collagerPPM = new CollagerPPM();

    try {
      new CollagerControllerImpl(System.out, new InputStreamReader(System.in), collagerPPM).run();
    } catch (IOException e) {
      throw new RuntimeException();
    }

  }
}

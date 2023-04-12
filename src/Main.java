import java.io.IOException;
import java.util.Objects;

import javax.swing.UnsupportedLookAndFeelException;

import controller.CollagerController;
import controller.CollagerControllerImpl;
import view.CollagerView;
import view.CollagerViewImpl;

/**
 * A class to run CollagerPPM.
 */
public class Main {

  /**
   * Runs the collager program.
   *
   * @param args arguments.
   * @throws IOException if the controller catches an exception.
   */
  public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException,
          ClassNotFoundException, InstantiationException, IllegalAccessException {
    Appendable appendable = System.out;
    CollagerView view = new CollagerViewImpl();
    CollagerController controller = new CollagerControllerImpl(appendable, view);
    if (args.length != 0) {
      if (Objects.equals(args[0], "-file")) {
        controller.runScriptMode(
                "C:\\Users\\jdhoo\\Documents\\GitHub\\collager\\res\\script.txt");
      }
      if (Objects.equals(args[0], "-text")) {
        controller.run();
      }
    } else {
      controller.runGUIMode();
    }
  }
}
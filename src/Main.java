import java.io.IOException;
import java.util.Objects;

import javax.swing.UnsupportedLookAndFeelException;

import controller.CollagerController;
import controller.CollagerControllerImpl;
import view.CollagerView;
import view.CollagerViewImpl;

/**
 * A class to run the collager program. The program can run with different arguments, either '-file' or '-text', if
 * neither of these is given than it runs the program in GUI mode.
 */
public class Main {

  /**
   * Runs the collager program.
   *
   * @param args either '-file' or '-text' which controls which version of collager is run, if none are given
   *             the default is run in GUI mode.
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
package view;

import javax.swing.UnsupportedLookAndFeelException;

/**
 * This interface represents an interactive view of the Collager program with a GUI.
 */
public interface CollagerView {

  /**
   * Creates the main frame of the view.
   *
   * @throws UnsupportedLookAndFeelException if the look & feel management classes are not present.
   * @throws ClassNotFoundException if a class cannot be found.
   * @throws InstantiationException if an object cannot be instantiated.
   * @throws IllegalAccessException if a class, field, method, or constructor cannot be accessed.
   */
  void createMainFrame() throws UnsupportedLookAndFeelException, ClassNotFoundException,
          InstantiationException, IllegalAccessException;
}

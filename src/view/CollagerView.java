package view;

import java.awt.event.ActionListener;

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

  /**
   * Creates a menubar for the view with components: File & Edit.
   */
  void createMenuBar();

  /**
   * Creates an image panel for displaying the composite image from the project that is currently
   * being worked on.
   */
  void createImagePanel();

  /**
   * Initialize the view.
   */
  void initializeView() throws UnsupportedLookAndFeelException, ClassNotFoundException,
          InstantiationException, IllegalAccessException;

  /**
   * Sets the action listeners for all menubar options.
   *
   * @param listener the input action listener.
   */
  void setActionListeners(ActionListener listener);
}

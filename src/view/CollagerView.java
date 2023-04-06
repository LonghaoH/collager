package view;

import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
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
   * Creates a panel for displaying the list of layers in the current project.
   */
  void createLayerPanel();

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

  /**
   * Gets the main panel of the view.
   *
   * @return the main panel.
   */
  JPanel getMainPanel();

  /**
   * Update the current composite.
   *
   * @param image the image of the composite.
   */
  void updateComposite(Image image);

  /**
   * Update the layers panel.
   *
   * @param layerName the layer name of the layer.
   */
  void updateLayer(String layerName);

  void refresh();
}

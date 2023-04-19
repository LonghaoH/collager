package model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a collager program. Allows user to have multiple image layers and apply filters to
 * the layers.
 */
public interface ICollager {

  /**
   * Initializes a new project with a background layer.
   *
   * @param height the height of the new project
   * @param width  the width of the new project
   * @throws IllegalArgumentException if width and height are invalid
   */
  void newProject(int height, int width) throws IllegalArgumentException;

  /**
   * Saves the collager project to the given destination.
   *
   * @param destination the destination to save the project to.
   */
  void saveProject(String destination) throws IOException;

  /**
   * Saves the current project as an image to the given destination with the desired file
   * extension, as well as other specs of the image.
   *
   * @param destination the destination to save the project to.
   * @param height the height of the image.
   * @param width the width of the image.
   * @param maxVal the max value of the image.
   * @param pixels the pixels information of the image.
   * @param extension the String representation of the file extension.
   * @param image the image file.
   * @throws IOException if the file already exists.
   */
  void saveImage(String destination, int height, int width, int maxVal, PixelColor[][] pixels,
                 String extension, ImageFiles image) throws IOException;

  /**
   * Adds a default layer to the collager. A default layer is a fully white layer with full transparency
   * (alpha channel 0).
   *
   * @param name the name of the layer
   * @throws IllegalArgumentException if the name is already in the collager
   */
  void addLayer(String name) throws IllegalArgumentException;

  /**
   * Adds an image to the layer.
   *
   * @param layerName the name of the layer
   * @param imageName the name of the image
   * @param xPos      the x-position on the layer of where the image will be placed
   * @param yPos      the y-position on the layrer of where the image will be placed
   */
  void addImageToLayer(String layerName, String imageName, int xPos, int yPos) throws IOException;

  /**
   * Sets the filter of the given layer to the filter option given.
   *
   * @param layerName    the name of the layer
   * @param filterOption the name of the filter applied
   */
  void setFilter(String layerName, String filterOption);

  /**
   * Returns the height of the collager.
   *
   * @return the height of the collager
   */
  int getHeight();

  /**
   * Returns the width of the collager.
   *
   * @return the width of the collager
   */
  int getWidth();

  /**
   * Returns the maxVal of the collager.
   *
   * @return the maxVal of the collager
   */
  int getMaxVal();

  /**
   * Returns the layers in the current project.
   *
   * @return the layers in the collager
   */
  ArrayList<Layer> getLayers();

  /**
   * Return the specific layer in the current collager, if it exists.
   *
   * @return the specific layer.
   */
  Layer getLayer(String layerName) throws IllegalArgumentException;
}

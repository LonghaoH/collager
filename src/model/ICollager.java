package model;

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
   * Adds a default layer to the collager.
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
  void addImageToLayer(String layerName, String imageName, int xPos, int yPos);

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
}

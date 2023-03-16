package model;

import java.util.ArrayList;

/**
 * Represents a Collager program. Starts with a transparent background layer.
 */
public class Collager {
  private int height;
  private int width;
  private final int maxVal;
  private Layer background;
  private ArrayList<Layer> layers;

  /**
   * Initializes the Collager.
   */
  public Collager() {
    this.height = 0;
    this.width = 0;
    this.maxVal = 255;
    this.background = null;
    this.layers = new ArrayList<>();
  }

  /**
   * Initializes a new project with a background layer.
   *
   * @param height the height of the new project
   * @param width  the width of the new project
   * @throws IllegalArgumentException if width and height are invalid
   */
  public void newProject(int height, int width) throws IllegalArgumentException {
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("Dimensions cannot be negative");
    }
    this.height = height;
    this.width = width;
    this.background = new Layer("background", height, width, new Filter()).whiteLayer();
    this.layers.add(this.background);
  }

  /**
   * Loads a project with the given filepath.
   * @param filepath a String of the path to the given file
   */
  public void loadProject(String filepath) {

  }

  /**
   * Saves the project as one file.
   */
  public void saveProject() {

  }

  /**
   * Adds a default layer to the collager.
   *
   * @param name the name of the layer
   * @throws IllegalArgumentException if the name is already in the collager
   */
  public void addLayer(String name) throws IllegalArgumentException {
    for (Layer layer : this.layers) {
      if (layer.getName().equals(name)) {
        throw new IllegalArgumentException("Same name as other layer");
      }
    }
    this.layers.add(new Layer(name, this.height, this.width, new Filter()).defaultLayer());
  }

  public void addImageToLayer(String layerName, String imageName, int xPos, int yPos) {
    for (int i = 0; i < this.layers.size(); i++) {
      if (this.layers.get(i).getName().equals(layerName)) {
        this.layers.get(i).addImage(imageName, xPos, yPos);
      }
    }
  }

  /**
   * Sets the filter of the given layer to the filter option given.
   * @param layerName the name of the layer
   * @param filterOption ***maybe not a string change as needed
   */
  public void setFilter (String layerName, String filterOption) {

  }

  /**
   * Saves the image to a file given by its name.
   * @param fileName the name of the file
   */
  public void saveImage (String fileName) {

  }

  /**
   * Returns the height of the collager.
   *
   * @return the height of the collager
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Returns the width of the collager.
   *
   * @return the width of the collager
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the maxVal of the collager.
   *
   * @return the maxVal of the collager
   */
  public int getMaxVal() {
    return this.maxVal;
  }

  /**
   * Returns the layers in the current project.
   *
   * @return the layers in the collager
   */
  public ArrayList<Layer> getLayers() {
    return this.layers;
  }
}

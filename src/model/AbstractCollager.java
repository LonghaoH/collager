package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents an Abstract Collager Program. Can be used with any Image, and a specified maximum
 * value per pixel.
 */
public abstract class AbstractCollager implements ICollager {
  protected int height;
  protected int width;
  protected final int maxVal;
  protected Layer background;
  protected final ArrayList<Layer> layers;

  /**
   * Initializes the Collager.
   */
  public AbstractCollager(int maxVal) {
    this.height = 0;
    this.width = 0;
    this.maxVal = maxVal;
    this.background = null;
    this.layers = new ArrayList<>();
  }

  /**
   * Initializes a new project with a white background layer.
   *
   * @param height the height of the new project
   * @param width  the width of the new project
   * @throws IllegalArgumentException if width and height are invalid
   */
  @Override
  public void newProject(int height, int width) throws IllegalArgumentException {
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("Dimensions cannot be negative");
    }
    this.height = height;
    this.width = width;
    this.background = new Layer("background", height, width, this.maxVal).whiteLayer();
    this.layers.add(this.background);
  }

  /**
   * Loads a project with the given filepath.
   * @param filepath a String of the path to the given file
   */
  @Override
  public void loadProject(String filepath) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filepath));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filepath + " not found!");
    }

    /*
    determine file format then use String parsing to set the data from the file using newProject
    method.
     */
  }

  /**
   * Saves the project as one file.
   */
  @Override
  public void saveProject() {
    /*
    saves the project as specified file format. not sure about naming file or not.
     */
  }

  /**
   * Adds a default layer to the collager.
   *
   * @param name the name of the layer
   * @throws IllegalArgumentException if the name is already in the collager
   */
  @Override
  public void addLayer(String name) throws IllegalArgumentException {
    for (Layer layer : this.layers) {
      if (layer.getName().equals(name)) {
        throw new IllegalArgumentException("Same name as other layer");
      }
    }
    this.layers.add(new Layer(name, this.height, this.width, this.maxVal).defaultLayer());
  }

  /**
   * Adds an image to the layer.
   * @param layerName the name of the layer
   * @param imageName the name of the image
   * @param xPos the x-position on the layer of where the image will be placed
   * @param yPos the y-position on the layrer of where the image will be placed
   */
  @Override
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
   * @param filterOption the name of the filter applied
   */
  @Override
  public void setFilter (String layerName, String filterOption) {
    for (int i = 0; i < this.layers.size(); i++) {
      if (this.layers.get(i).getName().equals(layerName)) {
        this.layers.get(i).filter(filterOption);
      }
    }
  }

  /**
   * Saves the image to a file given by its name.
   * @param fileName the name of the file
   */
  @Override
  public void saveImage (String fileName) {
    /*
    overwrites the file by producing the final image of the project.
     */
  }

  /**
   * Returns the height of the collager.
   *
   * @return the height of the collager
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Returns the width of the collager.
   *
   * @return the width of the collager
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the maxVal of the collager.
   *
   * @return the maxVal of the collager
   */
  @Override
  public int getMaxVal() {
    return this.maxVal;
  }

  /**
   * Returns the layers in the current project.
   *
   * @return the layers in the collager
   */
  @Override
  public ArrayList<Layer> getLayers() {
    return this.layers;
  }
}

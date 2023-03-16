package model;

/**
 * Represents a layer in the Collager Program. Has a name, a height and width, and a filter name.
 */
public class Layer {
  private String name;
  private final int height;
  private final int width;
  private model.Filter filter;
  private PixelColor[][] layerImage;

  /**
   * Initializes the layer.
   * @param name the name of the layer
   * @param height the height of the layer
   * @param width the width of the layer
   * @param filter the filter applied to the layer
   */
  public Layer(String name, int height, int width, Filter filter) {
    this.name = name;
    this.height = height;
    this.width = width;
    this.filter = filter;
    this.layerImage = new PixelColor[this.height][this.width];
  }

  /**
   * Returns the name of the layer.
   * @return the name of the layer
   */
  public String getName() {
    return this.name;
  }

  /**
   * Makes the layer fully white with no transparency.
   * @return a fully white layer
   */
  public Layer whiteLayer() {
    for (int i = 0; i < height; i++) {
      for (int k = 0; k < width; k++) {
        this.layerImage[i][k] = new PixelColor(255, 255, 255, 255);
      }
    }
    return this;
  }

  /**
   * Makes the layer a default layer
   * @return a white layer with full transparency
   */
  public Layer defaultLayer() {
    for (int i = 0; i < height; i++) {
      for (int k = 0; k < width; k++) {
        this.layerImage[i][k] = new PixelColor(255, 255, 255, 0);
      }
    }
    return this;
  }

  /**
   * Adds an image to the layer
   * @param imageName the filepath of the image to be added
   * @param xPos the x-position of the top-leftmost corner of the image
   * @param yPos the y-position of the top-leftmost corner of the image
   */
  public void addImage(String imageName, int xPos, int yPos) {
  }
}

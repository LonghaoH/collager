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

  public Layer(String name, int height, int width, Filter filter) {
    this.name = name;
    this.height = height;
    this.width = width;
    this.filter = filter;
    this.layerImage = new PixelColor[this.height][this.width];
  }

  public Layer whiteLayer() {
    for (int i = 0; i < height; i++) {
      for (int k = 0; k < width; k++) {
        this.layerImage[i][k] = new PixelColor(255, 255, 255, 255);
      }
    }
    return this;
  }

  public Layer defaultLayer() {
    for (int i = 0; i < height; i++) {
      for (int k = 0; k < width; k++) {
        this.layerImage[i][k] = new PixelColor(255, 255, 255, 0);
      }
    }
    return this;
  }


}

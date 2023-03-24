package model;

import java.util.Objects;

/**
 * Represents a layer in a Collager Program. Has a name, a height and width, and a filter name.
 */
public class Layer {
  private String name;
  private final int height;
  private final int width;
  private final int maxVal;
  private IFilter appliedFilter;
  private String filter;
  protected final PixelColor[][] layerImage;

  /**
   * Initializes the layer.
   *
   * @param name   the name of the layer
   * @param height the height of the layer
   * @param width  the width of the layer
   * @throws IllegalArgumentException if the arguments are null or invalid
   */
  public Layer(String name, int height, int width, int maxVal) throws IllegalArgumentException {
    if (Objects.isNull(name) || height < 0 || width < 0 || maxVal <= 0) {
      throw new IllegalArgumentException("Invalid Arguments");
    } else {
      this.name = name;
      this.height = height;
      this.width = width;
      this.maxVal = maxVal;
      this.appliedFilter = new FilterImpl(new PixelColor(0, 0, 0, 0));
      this.filter = "normal";
      this.layerImage = new PixelColor[this.height][this.width];
    }
  }

  /**
   * Returns the name of the layer.
   *
   * @return the name of the layer
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the height of the layer.
   *
   * @return the height of the layer
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Returns the width of the layer.
   *
   * @return the width of the layer
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the maximum value of the layer.
   *
   * @return the maximum value of the layer
   */
  public int getMaxVal() {
    return this.maxVal;
  }

  /**
   * Returns the filter on the layer.
   *
   * @return the filter of the layer
   */
  public String getFilter() {
    return this.filter;
  }

  /**
   * Returns the image representation of the layer.
   *
   * @return the image representation of the layer
   */
  public PixelColor[][] getLayerImage() {
    return this.layerImage;
  }

  /**
   * Makes the layer fully white with no transparency.
   *
   * @return a fully white layer
   */
  public Layer whiteLayer() {
    for (int i = 0; i < height; i++) {
      for (int k = 0; k < width; k++) {
        this.layerImage[i][k] = new PixelColor(this.maxVal, this.maxVal, this.maxVal, this.maxVal);
      }
    }
    return this;
  }

  /**
   * Makes the layer a default layer.
   *
   * @return a white layer with full transparency
   */
  public Layer defaultLayer() {
    for (int i = 0; i < height; i++) {
      for (int k = 0; k < width; k++) {
        this.layerImage[i][k] = new PixelColor(this.maxVal, this.maxVal, this.maxVal, 0);
      }
    }
    return this;
  }

  /**
   * Adds an image to the layer.
   *
   * @param imageName the filepath of the image to be added
   * @param xPos      the x-position of the top-leftmost corner of the image
   * @param yPos      the y-position of the top-leftmost corner of the image
   */
  public void addImage(String imageName, int xPos, int yPos) {
    PixelColor[][] image = ImageUtil.readPPM(imageName);
    int imageHeight = ImageUtil.getHeight(imageName);
    int imageWidth = ImageUtil.getWidth(imageName);

    int i = yPos;
    int k = xPos;

    for (int j = 0; j < imageHeight; j++) {
      for (int m = 0; m < imageWidth; m++) {
        if (i >= this.height || k >= this.width) {
          break;
        }
        PixelColor pix = image[j][m];
        this.layerImage[i][k] = pix;
        i++;
        k++;
      }
    }

  }

  /**
   * Applies a filter to the layer.
   *
   * @param filType the name of the type of filter to be applied
   * @throws IllegalArgumentException if the filter type given is not known
   */
  public void filter(String filType) throws IllegalArgumentException {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.appliedFilter = new FilterImpl(this.layerImage[i][j]);
        this.appliedFilter.applyFilter(filType);
      }
    }
    this.filter = filType;
  }
}

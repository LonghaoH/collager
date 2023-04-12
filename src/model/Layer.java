package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Represents a layer in a Collager Program. Has a name, a height and width, and a filter name.
 */
public class Layer implements ILayer {
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
  public void addImage(String imageName, int xPos, int yPos) throws IOException {
    ImageFiles image = new ImageFiles(imageName);
    PixelColor[][] imagePixel;
    int imageHeight;
    int imageWidth;
    if (image.getExtension().equals("PPM")) {
      imagePixel = CollagerUtil.readPPM(imageName);
      imageHeight = CollagerUtil.getHeight(imageName);
      imageWidth = CollagerUtil.getWidth(imageName);
    } else {
      imagePixel = image.toPixelImage();
      imageHeight = image.getHeight();
      imageWidth = image.getWidth();
    }

    for (int j = yPos; j < imageHeight + yPos; j++) {
      for (int m = xPos; m < imageWidth + xPos; m++) {
        if (j >= this.height || m >= this.width) {
          break;
        }
        PixelColor pix = imagePixel[j - yPos][m - xPos];
        pix = new PixelColor(pix.getRed(), pix.getGreen(), pix.getBlue(), this.maxVal);
        this.layerImage[j][m] = pix;
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

  /**
   * Applies a blending filter to the layer.
   *
   * @param filType   the name of the type of filter to apply
   * @param compImage the image composite of all the layers below this
   */
  public void filterBlend(String filType, PixelColor[][] compImage) {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.appliedFilter = new FilterImpl(this.layerImage[i][j]);
        this.appliedFilter.applyBlendFilter(filType, compImage[i][j]);
      }
    }
    this.filter = filType;
  }

  @Override
  public BufferedImage convertToBuffered() {
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = layerImage[i][j].getRed();
        int g = layerImage[i][j].getGreen();
        int b = layerImage[i][j].getBlue();
        int rgb = (r << 16) | (g << 8) | b;
        image.setRGB(j, i, rgb);
      }
    }
    return image;
  }

  @Override
  public void setFilterName(String filter) {
    this.filter = filter;
  }
}

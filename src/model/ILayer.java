package model;

import java.awt.image.BufferedImage;

/**
 * Represents a layer of the collager program. Has a name, layer image, height, width, maximum value
 * and a filter.
 */
public interface ILayer {
  /**
   * Returns the name of the layer.
   *
   * @return the name of the layer
   */
  String getName();

  /**
   * Returns the height of the layer.
   *
   * @return the height of the layer
   */
  int getHeight();

  /**
   * Returns the width of the layer.
   *
   * @return the width of the layer
   */
  int getWidth();

  /**
   * Returns the maximum value of the layer.
   *
   * @return the maximum value of the layer
   */
  int getMaxVal();

  /**
   * Returns the filter on the layer.
   *
   * @return the filter of the layer
   */
  String getFilter();

  /**
   * Returns the image representation of the layer.
   *
   * @return the image representation of the layer
   */
  PixelColor[][] getLayerImage();

  /**
   * Makes the layer fully white with no transparency.
   *
   * @return a fully white layer
   */
  Layer whiteLayer();

  /**
   * Makes the layer a default layer.
   *
   * @return a white layer with full transparency
   */
  Layer defaultLayer();

  /**
   * Adds an image to the layer.
   *
   * @param imageName the filepath of the image to be added
   * @param xPos      the x-position of the top-leftmost corner of the image
   * @param yPos      the y-position of the top-leftmost corner of the image
   */
  void addImage(String imageName, int xPos, int yPos);

  /**
   * Applies a filter to the layer.
   *
   * @param filType the name of the type of filter to be applied
   * @throws IllegalArgumentException if the filter type given is not known
   */
  void filter(String filType);

  /**
   * Applies a blending filter to the layer.
   *
   * @param filType the name of the type of filter to apply
   * @param compImage the image composite of all the layers below this
   */
  void filterBlend(String filType, PixelColor[][] compImage);

  /**
   * Converts the current layer to a BufferedImage.
   *
   * @return a BufferedImage.
   */
  BufferedImage convertToBuffered();

  /**
   * Sets the filter of the layer.
   */
  void setFilter(String filter);
}

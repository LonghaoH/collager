package model;

/**
 * This interface contains the filters that can be applied to the pixels of images on a layer. Applies a filter
 * individually to a pixel, and the Layer class applies that change to all the pixels in the layer.
 */
public interface IFilter {

  /**
   * Applies a filter to a pixel.
   *
   * @param filType the name of the type of filter to be applied onto the pixel.
   * @throws IllegalArgumentException if the filter type given is not known
   */
  void applyFilter(String filType) throws IllegalArgumentException;

  /**
   * Applies a blending filter to a pixel.
   *
   * @param filType the name of the type of filter to be applied onto the pixel.
   * @param comp the pixel of the composite layers below the layer
   * @throws IllegalArgumentException if the filter type given is not known
   */
  void applyBlendFilter(String filType, PixelColor comp);
}
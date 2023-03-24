package model;

/**
 * This interface contains the filters that can be applied to the pixels of images on a layer.
 */
public interface IFilter {

  /**
   * Applies a filter to a pixel.
   *
   * @param filType the name of the type of filter to be applied onto the pixel.
   * @throws IllegalArgumentException if the filter type given is not known
   */
  void applyFilter(String filType) throws IllegalArgumentException;
}
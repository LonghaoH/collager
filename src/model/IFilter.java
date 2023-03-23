package model;

/**
 * This interface contains the filters that can be applied to images on a layer.
 */
public interface IFilter {

  /**
   * Applies a filter to the layer.
   *
   * @param filType the name of the type of filter to be applied
   * @throws IllegalArgumentException if the filter type given is not known
   */
  public void applyFilter(String filType) throws IllegalArgumentException;
}
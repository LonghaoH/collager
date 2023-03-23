package model;

/**
 * This class is an implementation of the Filter interface, allowing users to apply various filters
 * to an image.
 */
public class FilterImpl implements IFilter {
  private PixelColor pixel;

  /**
   * Constructor: Initiates a filter implementation given a PixelColor.
   *
   * @throws IllegalArgumentException if the given PixelColor is null.
   */
  public FilterImpl(PixelColor pixel) throws IllegalArgumentException {
    if (pixel == null) {
      throw new IllegalArgumentException("Pixels cannot be null.");
    }
    this.pixel = pixel;
  }

  @Override
  public void applyFilter(String filType) throws IllegalArgumentException {
    return;
  }
}

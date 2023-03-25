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
    switch (filType) {
      case "normal":
        break;
      case "red-component":
        this.pixel.colComponent("red");
        break;
      case "green-component":
        this.pixel.colComponent("green");
        break;
      case "blue-component":
        this.pixel.colComponent("blue");
        break;
      case "brighten-value":
        this.pixel.brighten("value");
        break;
      case "brighten-intensity":
        this.pixel.brighten("intensity");
        break;
      case "brighten-luma":
        this.pixel.brighten("luma");
        break;
      case "darken-value":
        this.pixel.darken("value");
        break;
      case "darken-intensity":
        this.pixel.darken("intensity");
        break;
      case "darken-luma":
        this.pixel.darken("luma");
        break;
      default:
        throw new IllegalArgumentException("Filter type unknown.");
    }
  }

  @Override
  public void applyBlendFilter(String filType, PixelColor comp) throws IllegalArgumentException {
    switch (filType) {
      case "difference":
        this.pixel.difference(comp);
        break;
      case "multiply":
        this.pixel.multiply(comp);
        break;
      case "screen":
        this.pixel.screen(comp);
        break;
    }
  }
}

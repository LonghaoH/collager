package model;

import java.util.Objects;

/**
 * This class represents a single pixel, containing all of its data values.
 */
public class PixelValue {
  private final int red;
  private final int green;
  private final int blue;
  private final int value;
  private final double intensity;
  private final double luma;

  /**
   * Constructor: Initialize an instance of PixelValue and assuming the max values for RGB is 255.
   *
   * @param red the red value.
   * @param green the green value.
   * @param blue the blue value.
   * @throws IllegalArgumentException if any of the given RGB values is negative.
   */
  public PixelValue(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || green < 0 || blue < 0) {
      throw new IllegalArgumentException("Negative RGB values.");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.value = Math.max(red, Math.max(green, blue));
    this.intensity = (red + green + blue) / 3.0;
    this.luma = (0.2126 * red) + (0.7152 * green) + (0.0722 * blue);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PixelValue pixel = (PixelValue) o;
    return this.red == pixel.red && this.green == pixel.green && this.blue == pixel.blue;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.red, this.green, this.blue);
  }
}

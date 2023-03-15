package model;

import java.util.Objects;

/**
 * This class represents the color values of a single pixel.
 */
public class PixelColor {
  private final int alpha;
  private final int red;
  private final int green;
  private final int blue;
  private final int value;
  private final double intensity;
  private final double luma;

  /**
   * Constructor: Represents a 4-components pixel given the alpha and RGB values,
   * assuming the max value is 255.
   *
   * @param r the red component.
   * @param g the green component.
   * @param b the blue component.
   * @param a the alpha value.
   * @throws IllegalArgumentException if any of the given alpha or RGB values is negative.
   */
  public PixelColor(int r, int g, int b, int a) throws IllegalArgumentException {
    if (r < 0 || g < 0 || b < 0 || a < 0) {
      throw new IllegalArgumentException("Alpha and RGB values cannot be negative.");
    }
    alpha = a;
    red = Objects.requireNonNull(r);
    green = Objects.requireNonNull(g);
    blue = Objects.requireNonNull(b);
    value = Math.max(red, Math.max(green, blue));
    intensity = (red + green + blue) / 3.0;
    luma = (0.2126 * red) + (0.7152 * green) + (0.0722 * blue);
  }

  /**
   * Constructor: Represents a 3-components pixel given the RGB values,
   * assuming the max value is 255.
   *
   * @param r the red component.
   * @param g the green component.
   * @param b the blue component.
   * @throws IllegalArgumentException if any of the given RGB values is negative.
   */
  public PixelColor(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || g < 0 || b < 0) {
      throw new IllegalArgumentException("RGB values cannot be negative.");
    }
    alpha = 255;
    red = Objects.requireNonNull(r);
    green = Objects.requireNonNull(g);
    blue = Objects.requireNonNull(b);
    value = Math.max(red, Math.max(green, blue));
    intensity = (red + green + blue) / 3.0;
    luma = (0.2126 * red) + (0.7152 * green) + (0.0722 * blue);
  }

  /**
   * Transparency: Calculate the RGB components as the average of the background image and
   * the regular image weighted by the alpha value.
   *
   * @param dr the red component of the background pixel.
   * @param dg the green component of the background pixel.
   * @param db the blue component of the background pixel.
   * @param da the alpha value of the background pixel.
   * @return an updated 4-components PixelColor.
   */
  public PixelColor layerColor(int dr, int dg, int db, int da) {
    int a;
    int r;
    int g;
    int b;
    double aPercent = alpha / 255.0;
    double daPercent = da / 255.0;
    double alphaPercent = aPercent + daPercent * (1.0 - aPercent);
    a = (int) (alphaPercent * 255.0);
    r = (int) (aPercent * red + dr * daPercent * (1.0 - aPercent) * (1.0 / alphaPercent));
    g = (int) (aPercent * green + dg * daPercent * (1.0 - aPercent) * (1.0 / alphaPercent));
    b = (int) (aPercent * blue + db * daPercent * (1.0 - aPercent) * (1.0 / alphaPercent));
    return new PixelColor(r, g, b, a);
  }

  /**
   * Convert a 4-components pixel representation to a 3-components pixel representation.
   *
   * @return a 3-components PixelColor.
   */
  public PixelColor convertTo3Components() {
    double percent = alpha / 255.0;
    int r = (int) (red * percent);
    int g = (int) (green * percent);
    int b = (int) (blue * percent);
    return new PixelColor(r, g, b);
  }

  /**
   * Get the red component's value.
   *
   * @return red value.
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Get the green component's value.
   *
   * @return green value.
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Get the blue component's value.
   *
   * @return blue value.
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * Get the alpha value.
   *
   * @return alpha value.
   */
  public int getAlpha() {
    return this.alpha;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PixelColor that = (PixelColor) o;
    return this.alpha == that.alpha
            && this.red == that.red
            && this.green == that.green
            && this.blue == that.blue;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.alpha, this.red, this.green, this.blue);
  }
}

package model;

import java.util.Objects;

import static java.lang.Math.abs;
import static model.RepresentationConverter.convertHSLtoRGB;
import static model.RepresentationConverter.convertRGBtoHSL;

/**
 * This class represents the color values of a single pixel.
 */
public class PixelColor {
  private final int alpha;
  private int red;
  private int green;
  private int blue;
  private final int value;
  private final double intensity;
  private final double luma;

  /**
   * Constructor: Represents a 4-components pixel given the alpha and RGB values.
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
    alpha = Objects.requireNonNull(a);
    red = Objects.requireNonNull(r);
    green = Objects.requireNonNull(g);
    blue = Objects.requireNonNull(b);
    value = Math.max(red, Math.max(green, blue));
    intensity = (red + green + blue) / 3.0;
    luma = (0.2126 * red) + (0.7152 * green) + (0.0722 * blue);
  }

  /**
   * Constructor: Represents a 3-components pixel given the RGB values.
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
    double percent = (double) alpha / 255;
    int r = (int) (red * percent);
    int g = (int) (green * percent);
    int b = (int) (blue * percent);
    return new PixelColor(r, g, b);
  }

  /**
   * Changes the pixel to the color component of the color given.
   *
   * @param type the color component to filter on
   * @throws IllegalArgumentException if the given type is not supported
   */
  public void colComponent(String type) throws IllegalArgumentException {
    switch (type) {
      case "red":
        this.green = 0;
        this.blue = 0;
        break;
      case "green":
        this.red = 0;
        this.blue = 0;
        break;
      case "blue":
        this.red = 0;
        this.green = 0;
        break;
      default:
        throw new IllegalArgumentException("Not supported color");
    }
  }

  /**
   * Brightens the pixel either using value, intensity, or luma.
   *
   * @param type the type of brightening method to use
   * @throws IllegalArgumentException if the type of filter is not recognized
   */
  public void brighten(String type) throws IllegalArgumentException {
    switch (type) {
      case "value":
        this.red += this.value;
        if (this.red > 255) {
          this.red = 255;
        }

        this.green += this.value;
        if (this.green > 255) {
          this.green = 255;
        }

        this.blue += this.value;
        if (this.blue > 255) {
          this.blue = 255;
        }
        break;
      case "intensity":
        this.red += this.intensity;
        if (this.red > 255) {
          this.red = 255;
        }

        this.green += this.intensity;
        if (this.green > 255) {
          this.green = 255;
        }

        this.blue += this.intensity;
        if (this.blue > 255) {
          this.blue = 255;
        }
        break;
      case "luma":
        this.red += this.luma;
        if (this.red > 255) {
          this.red = 255;
        }

        this.green += this.luma;
        if (this.green > 255) {
          this.green = 255;
        }

        this.blue += this.luma;
        if (this.blue > 255) {
          this.blue = 255;
        }
        break;
      default:
        throw new IllegalArgumentException("Filter type not supported.");
    }
  }

  /**
   * Darkens the pixel either using value, intensity, or luma.
   *
   * @param type the type of darkening method to use
   * @throws IllegalArgumentException if the type of filter is not recognized
   */
  public void darken(String type) throws IllegalArgumentException {
    switch (type) {
      case "value":
        this.red -= this.value;
        if (this.red < 0) {
          this.red = 0;
        }

        this.green -= this.value;
        if (this.green < 0) {
          this.green = 0;
        }

        this.blue -= this.value;
        if (this.blue < 0) {
          this.blue = 0;
        }
        break;
      case "intensity":
        this.red -= this.intensity;
        if (this.red < 0) {
          this.red = 0;
        }

        this.green -= this.intensity;
        if (this.green < 0) {
          this.green = 0;
        }

        this.blue -= this.intensity;
        if (this.blue < 0) {
          this.blue = 0;
        }
        break;
      case "luma":
        this.red -= this.luma;
        if (this.red < 0) {
          this.red = 0;
        }

        this.green -= this.luma;
        if (this.green < 0) {
          this.green = 0;
        }

        this.blue -= this.luma;
        if (this.blue < 0) {
          this.blue = 0;
        }
        break;
      default:
        throw new IllegalArgumentException("Filter type not supported.");
    }
  }

  /**
   * Applies a difference filter to the pixel based on the composite pixel below it.
   * @param composite a composite of all the pixels beneath it.
   */
  public void difference(PixelColor composite) {
    int dr = composite.getRed();
    int dg = composite.getGreen();
    int db = composite.getBlue();
    int red = this.red;
    int green = this.green;
    int blue = this.blue;
    this.red = abs(red - dr);
    this.green = abs(green - dg);
    this.blue = abs(blue - db);
  }

  /**
   * Applies a multiply filter to the pixel based on the composite pixel below it.
   * @param composite a composite of all the pixels beneath it.
   */
  public void multiply(PixelColor composite) {
    double[] hsl = convertRGBtoHSL(
            (double) this.red / 255.0,
            (double) this.green / 255.0,
            (double) this.blue / 255.0);
    double h = hsl[0];
    double s = hsl[1];
    double l = hsl[2];

    double[] dHdSdL = convertRGBtoHSL(
            (double) composite.getRed() / 255.0,
            (double) composite.getGreen() / 255.0,
            (double) composite.getBlue() / 255.0);
    double dH = dHdSdL[0];
    double dS = dHdSdL[1];
    double dL = dHdSdL[2];

    double[] rgbNew = convertHSLtoRGB(h, s,l * dL);
    this.red = (int) rgbNew[0];
    this.green = (int) rgbNew[1];
    this.blue = (int) rgbNew[2];
  }

  /**
   * Applies a screen filter to the pixel based on the composite pixel below it.
   * @param composite a composite of all the pixels beneath it.
   */
  public void screen(PixelColor composite) {
    double[] hsl = convertRGBtoHSL(
            (double) this.red / 255, (double) this.green / 255, (double) this.blue / 255);
    double h = hsl[0];
    double s = hsl[1];
    double l = hsl[2];

    double[] dHdSdL = convertRGBtoHSL(
            (double) composite.getRed() / 255,
            (double) composite.getGreen() / 255,
            (double) composite.getBlue() / 255);
    double dH = dHdSdL[0];
    double dS = dHdSdL[1];
    double dL = dHdSdL[2];

    double[] rgbNew = convertHSLtoRGB(h, s,(1 - ((1 - l) * (1 - dL))));
    this.red = (int) rgbNew[0];
    this.green = (int) rgbNew[1];
    this.blue = (int) rgbNew[2];
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

package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class represents an image file with different supported file extensions.
 */
public class ImageFiles {
  private final int height;
  private final int width;
  private final String filePath;
  private final BufferedImage image;

  /**
   * Supported types of extensions.
   */
  public enum Extension {
    PPM, JPEG, PNG
  }

  /**
   * Constructor: initiates an image file given the path of the file.
   *
   * @param filePath the path of the given file.
   * @throws IOException if the file is not found.
   */
  public ImageFiles(String filePath) throws IOException {
    this.filePath = filePath;
    this.image = ImageIO.read(new File(filePath));
    this.height = image.getHeight();
    this.width = image.getWidth();
  }

  /**
   * Constructor: initiates an image file given a BufferedImage.
   *
   * @param image the given BufferedImage.
   * @throws IOException if the file is not found.
   */
  public ImageFiles(BufferedImage image) throws IOException {
    this.filePath = "";
    this.image = image;
    this.height = image.getHeight();
    this.width = image.getWidth();
  }

  /**
   * Writes an image to a file with a given destination and extension.
   *
   * @param filePath the destination for writing the file to.
   * @param extension the desired extension of the file.
   * @throws IOException if the file already exists.
   */
  public void writeImageToFile(String filePath, Extension extension) throws IOException {
    ImageIO.write(this.image, extension.toString(), new File(filePath));
  }

  /**
   * Gets the file path to the image.
   *
   * @return the file path.
   */
  public String getFilePath() {
    return this.filePath;
  }

  /**
   * Gets the image.
   *
   * @return the image.
   */
  public BufferedImage getImage() {
    return this.image;
  }

  /**
   * Gets the height of the image.
   *
   * @return the height of the image.
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Gets the width of the image.
   *
   * @return the width of the image.
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Gets the extension of an image file.
   *
   * @return the extension of the image file.
   */
  public Extension getExtension() {
    String[] str = filePath.split("\\.");
    String extension = str[str.length - 1];
    return Extension.valueOf(extension.toUpperCase());
  }

  public PixelColor[][] toPixelImage() throws FileNotFoundException {
    int h = image.getHeight();
    int w = image.getWidth();
    PixelColor[][] ppmImage = new PixelColor[h][w];
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        int rgb = image.getRGB(j, i);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = (rgb) & 0xFF;
        ppmImage[i][j] = new PixelColor(red, green, blue);
      }
    }
    return ppmImage;
  }
}

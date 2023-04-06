package model;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class CollagerUtil {

  /**
   * Read an image file in the PPM format and convert it to a 2d array of PixelColor.
   *
   * @param filename the path of the file.
   */
  public static PixelColor[][] readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    PixelColor[][] pixelArray = new PixelColor[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        pixelArray[i][j] = new PixelColor(r, g, b);
      }
    }
    return pixelArray;
  }

  /**
   * Returns the width of the given ppm file.
   *
   * @param filename the path to the file
   * @return an int of the width of the ppm file
   */
  public static int getWidth(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    return width;
  }

  /**
   * Returns the height of the given ppm file.
   *
   * @param filename the path to the file
   * @return an int of the height of the ppm file
   */
  public static int getHeight(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    return height;
  }

  /**
   * Reads a collager file.
   *
   * @param filename the path to the file
   * @return a collager from the collager file
   */
  public static AbstractCollager readCollager(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("C1")) {
      throw new IllegalArgumentException("Invalid Collager file");
    }

    AbstractCollager collager = new CollagerPPM();

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    collager.height = height;
    collager.width = width;
    collager.maxVal = maxValue;

    while (sc.hasNext()) {

      String layerName = sc.next();
      String filterName = sc.next();

      Layer layer = new Layer(layerName, height, width, maxValue);

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          int a = sc.nextInt();

          layer.layerImage[i][j] = new PixelColor(r, g, b, a);
        }
      }

      layer.setFilterName(filterName);

      collager.layers.add(layer);
    }

    return collager;
  }
}


package model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents an Abstract Collager Program. Can be used with any Image, and a specified maximum
 * value per pixel.
 */
public abstract class AbstractCollager implements ICollager {
  protected int height;
  protected int width;
  protected int maxVal;
  protected final ArrayList<Layer> layers;

  /**
   * Initializes the Collager.
   */
  public AbstractCollager(int maxVal) {
    this.height = 0;
    this.width = 0;
    this.maxVal = maxVal;
    this.layers = new ArrayList<>();
  }

  /**
   * Initializes a new project with a white background layer.
   *
   * @param height the height of the new project
   * @param width  the width of the new project
   * @throws IllegalArgumentException if width and height are invalid
   */
  @Override
  public void newProject(int height, int width) throws IllegalArgumentException {
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("Dimensions cannot be negative");
    }
    this.height = height;
    this.width = width;
    this.layers.add(new Layer("background", height, width, this.maxVal).whiteLayer());
  }

  @Override
  public void saveProject(String destination) throws IOException {
    StringBuilder builder = new StringBuilder();
    builder.append("C1\n");
    builder.append(this.width).append(" ");
    builder.append(this.height).append("\n");
    builder.append(this.maxVal).append("\n");
    for (int i = 0; i < this.getLayers().size(); i++) {
      ILayer layer = this.getLayers().get(i);
      builder.append(layer.getName()).append(" ");
      builder.append(layer.getFilter()).append("\n");
      for (int j = 0; j < layer.getHeight(); j++) {
        for (int k = 0; k < layer.getWidth(); k++) {
          PixelColor currentPixel = layer.getLayerImage()[j][k];
          int red = currentPixel.getRed();
          int green = currentPixel.getGreen();
          int blue = currentPixel.getBlue();
          int alpha = currentPixel.getAlpha();
          if (i == this.getLayers().size() - 1
                  && j == layer.getHeight() - 1
                  && k == layer.getWidth() - 1) {
            builder.append(red).append(" ");
            builder.append(green).append(" ");
            builder.append(blue).append(" ");
            builder.append(alpha);
          } else {
            builder.append(red).append(" ");
            builder.append(green).append(" ");
            builder.append(blue).append(" ");
            builder.append(alpha).append("\n");
          }
        }
      }
    }
    Path file = Paths.get(destination);
    Files.write(file, Collections.singleton(builder), StandardCharsets.UTF_8);
  }

  @Override
  public void saveImage(String destination, int height, int width, int maxVal,
                        PixelColor[][] pixels, String extension, ImageFiles image)
          throws IOException {
    if (extension.equals("ppm")) {
      StringBuilder builder = new StringBuilder();
      builder.append("P3\n");
      builder.append(width).append(" ");
      builder.append(height).append("\n");
      builder.append(maxVal).append("\n");
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          PixelColor pixel = pixels[i][j];
          int red = pixel.getRed();
          int green = pixel.getGreen();
          int blue = pixel.getBlue();
          if (i == height - 1 && j == width - 1) {
            builder.append(red).append(" ");
            builder.append(green).append(" ");
            builder.append(blue);
          } else {
            builder.append(red).append(" ");
            builder.append(green).append(" ");
            builder.append(blue).append("\n");
          }
        }
      }
      Path file = Paths.get(destination);
      Files.write(file, Collections.singleton(builder), StandardCharsets.UTF_8);
    } else {
      ImageFiles.Extension extension1 = ImageFiles.Extension.valueOf(extension.toUpperCase());
      image.writeImageToFile(destination, extension1);
    }
  }

  /**
   * Adds a default layer to the collager.
   *
   * @param name the name of the layer
   * @throws IllegalArgumentException if the name is already in the collager
   */
  @Override
  public void addLayer(String name) throws IllegalArgumentException {
    for (Layer layer : this.layers) {
      if (layer.getName().equals(name)) {
        throw new IllegalArgumentException("Same name as other layer");
      }
    }
    this.layers.add(new Layer(name, this.height, this.width, this.maxVal).defaultLayer());
  }

  /**
   * Adds an image to the layer.
   *
   * @param layerName the name of the layer
   * @param imageName the name of the image
   * @param xPos      the x-position on the layer of where the image will be placed
   * @param yPos      the y-position on the layrer of where the image will be placed
   */
  @Override
  public void addImageToLayer(String layerName, String imageName, int xPos, int yPos)
          throws IOException {
    for (int i = 0; i < this.layers.size(); i++) {
      if (this.layers.get(i).getName().equals(layerName)) {
        this.layers.get(i).addImage(imageName, xPos, yPos);
      }
    }
  }

  /**
   * Sets the filter of the given layer to the filter option given.
   *
   * @param layerName    the name of the layer
   * @param filterOption the name of the filter applied
   */
  @Override
  public void setFilter(String layerName, String filterOption) {
    if (filterOption.equals("difference")
            || filterOption.equals("multiply") || filterOption.equals("screen")) {
      for (int i = 0; i < this.layers.size(); i++) {
        if (this.layers.get(i).getName().equals(layerName)) {
          PixelColor[][] comp = this.getLayersBelow(layerName);
          this.layers.get(i).filterBlend(filterOption, comp);
        }
      }
    } else {
      for (int i = 0; i < this.layers.size(); i++) {
        if (this.layers.get(i).getName().equals(layerName)) {
          this.layers.get(i).filter(filterOption);
        }
      }
    }
  }

  /**
   * Returns a composite 2d array of PixelColor that is the combination of all the layers below
   * the given layerName.
   *
   * @param layerName the name of the layer to get the composite beneath
   * @return a 2d array of PixelColor that is the composite of all layers beneath
   */
  public PixelColor[][] getLayersBelow(String layerName) {
    PixelColor[][] image = layers.get(0).getLayerImage();
    PixelColor[][] layerCurrent;
    for (int i = 0; i < this.layers.size(); i++) {
      if (this.layers.get(i).getName().equals(layerName)) {
        for (int w = 1; w < i - 1; w++) {
          layerCurrent = layers.get(w).getLayerImage();
          PixelColor[][] layerPrev = image;
          for (int j = 0; j < this.getHeight(); j++) {
            for (int k = 0; k < this.getWidth(); k++) {
              image[j][k] = layerCurrent[j][k].layerColor(
                      layerPrev[j][k].getRed(),
                      layerPrev[j][k].getGreen(),
                      layerPrev[j][k].getBlue(),
                      layerPrev[j][k].getAlpha()).convertTo3Components();
            }
          }
        }
      }
    }
    return image;
  }

  /**
   * Returns the height of the collager.
   *
   * @return the height of the collager
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Returns the width of the collager.
   *
   * @return the width of the collager
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the maxVal of the collager.
   *
   * @return the maxVal of the collager
   */
  @Override
  public int getMaxVal() {
    return this.maxVal;
  }

  /**
   * Returns the layers in the current project.
   *
   * @return the layers in the collager
   */
  @Override
  public ArrayList<Layer> getLayers() {
    return this.layers;
  }

  @Override
  public Layer getLayer(String layerName) throws IllegalArgumentException {
    for (int i = 0; i < this.getLayers().size(); i++) {
      if (this.getLayers().get(i).getName().equals(layerName)) {
        return this.getLayers().get(i);
      }
    }
    throw new IllegalArgumentException("Layer does not exist.");
  }
}

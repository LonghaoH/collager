package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import model.AbstractCollager;
import model.CollagerPPM;
import model.ImageUtil;
import model.Layer;
import model.PixelColor;

/**
 * Represents an implementation of the collager controller interface. It accepts commands from
 * the users, passes it to the model to be processed, and outputs messages for users to view.
 */
public class CollagerControllerImpl implements CollagerController {
  private final Appendable appendable;
  private final Readable input;
  private AbstractCollager currentCollager;

  /**
   * Constructor: Initializes the controller.
   *
   * @param appendable an appendable object for building messages.
   */
  public CollagerControllerImpl(Appendable appendable, Readable input, AbstractCollager collager) {
    this.appendable = Objects.requireNonNull(appendable);
    this.input = Objects.requireNonNull(input);
    this.currentCollager = collager;
  }

  @Override
  public void run() throws IOException {
    Scanner sc = new Scanner(input);
    int height;
    int width;
    String path;
    String layerName;
    String imageName;
    int xPos;
    int yPos;
    String filType;
    Layer layerOp = null;
    String fileName;
    menu();

    while (sc.hasNext()) {
      String in = sc.next();
      switch (in) {
        case "quit":
        case "q":
          return;
        case "new-project":
          height = Integer.parseInt(sc.next());
          width = Integer.parseInt(sc.next());
          try {
            currentCollager.newProject(height, width);
          } catch (IllegalArgumentException e) {
            throw new IOException(e.getMessage());
          }
          appendMessage("Created new canvas of " + height + "x" + width + ".\n");
          break;
        case "load-project":
          path = sc.next();
          currentCollager = ImageUtil.readCollager(path);
          appendMessage("Loaded project.");
          break;
        case "save-project":
          try {
            FileWriter file = new FileWriter("Project");
            file.write("C1" + "\n" + currentCollager.getWidth() + " " + currentCollager.getHeight()
                    + "\n" + currentCollager.getMaxVal() + "\n");
            for (int i = 0; i < currentCollager.getLayers().size(); i++) {
              Layer layer = currentCollager.getLayers().get(i);
              file.write(layer.getName() + " " +
                      layer.getFilter() + "\n");
              for (int k = 0; k < layer.getHeight(); k++) {
                for (int j = 0; j < layer.getWidth(); j++) {
                  PixelColor pixel = layer.getLayerImage()[k][j];
                  if(i == currentCollager.getLayers().size() -1 &&
                          k == layer.getHeight() -1 &&
                          j == layer.getWidth() - 1) {
                    file.write(pixel.getRed() + " " + pixel.getGreen() + " "
                            + pixel.getBlue() + " " + pixel.getAlpha());
                    break;
                  }
                  else {
                    file.write(pixel.getRed() + " " + pixel.getGreen() + " "
                            + pixel.getBlue() + " " + pixel.getAlpha() + "\n");
                  }
                }
              }
            }
            file.close();
          } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
          }
          appendMessage("Project saved.\n");
          break;
        case "add-layer":
          layerName = sc.next();
          try {
            currentCollager.addLayer(layerName);
          } catch (IllegalArgumentException e) {
            throw new IOException(e.getMessage());
          }
          appendMessage("Added layer " + layerName + " to current project.\n");
          break;
        case "add-image-to-layer":
          layerName = sc.next();
          imageName = sc.next();
          xPos = Integer.parseInt(sc.next());
          yPos = Integer.parseInt(sc.next());
          currentCollager.addImageToLayer(layerName, imageName, xPos, yPos);
          appendMessage("Added image " + imageName + " to layer " + layerName + " at ("
                  + xPos + ", " + yPos + ").\n");
          break;
        case "set-filter":
          layerName = sc.next();
          filType = sc.next();
          boolean contains = false;
          for (Layer layer : currentCollager.getLayers()) {
            if (layer.getName().equals(layerName)) {
              contains = true;
              break;
            }
          }
          if (!contains) {
            throw new IllegalArgumentException("Layer does not exist.");
          }
          for (Layer layer : currentCollager.getLayers()) {
            if (layer.getName().equals(layerName)) {
              layerOp = layer;
            }
          }
          try {
            layerOp.filter(filType);
          } catch (IllegalArgumentException e) {
            throw new IOException(e.getMessage());
          }
          appendMessage("The filter " + filType + " has been applied to layer "
                  + layerName + ".\n");
          break;
        case "save-image":
          fileName = sc.next();
          try {
            FileWriter file = new FileWriter(fileName);
            file.write("P3" + "\n" + currentCollager.getWidth() + " "
                    + currentCollager.getHeight() + "\n" + currentCollager.getMaxVal() + "\n");

            PixelColor[][] image =
                    currentCollager.getLayers().get(0).getLayerImage();
            PixelColor[][] layerCurrent = currentCollager.getLayers().get(0).getLayerImage();
            for (int i = 1; i < currentCollager.getLayers().size() - 1; i++) {
              layerCurrent = currentCollager.getLayers().get(i).getLayerImage();
              PixelColor[][] layerPrev = image;
              for (int j = 0; j < currentCollager.getHeight(); j++) {
                for (int k = 0; k < currentCollager.getWidth(); k++) {
                  image[j][k] = layerCurrent[j][k].layerColor(
                          layerPrev[j][k].getRed(),
                          layerPrev[j][k].getGreen(),
                          layerPrev[j][k].getBlue(),
                          layerPrev[j][k].getAlpha()).convertTo3Components();
                }
              }
            }

            for (int i = 0; i < currentCollager.getHeight(); i++) {
              for (int k = 0; k < currentCollager.getWidth(); k++) {
                if(i == currentCollager.getHeight() - 1 && k == currentCollager.getWidth() - 1) {
                  file.write(layerCurrent[i][k].getRed() + " " +
                          layerCurrent[i][k].getGreen() + " " +
                          layerCurrent[i][k].getBlue());
                }
                else {
                  file.write(layerCurrent[i][k].getRed() + " " +
                          layerCurrent[i][k].getGreen() + " " +
                          layerCurrent[i][k].getBlue() + "\n");
                }
              }
            }
            file.close();
          } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
          }
          appendMessage("Image has been saved to " + fileName + ".\n");
          break;
        default:
          System.out.print("Unknown command: " + in + ".\n");
          break;
      }
    }

  }

  /**
   * Appends the given message to the appendable.
   *
   * @param message the given message.
   * @throws IllegalStateException if there is no appendable, or if the given message is null.
   */
  private void appendMessage(String message) throws IllegalStateException {
    try {
      appendable.append(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Prints messages of the menu when running the controller.
   */
  private void menu() {
    appendMessage("Supported commands: " + System.lineSeparator());
    appendMessage("new-project canvas-height canvas-width" + System.lineSeparator());
    appendMessage("load-project path-to-project-file" + System.lineSeparator());
    appendMessage("save-project project-file-name" + System.lineSeparator());
    appendMessage("add-layer layer-name" + System.lineSeparator());
    appendMessage("add-image-to-layer layer-name image-name x-pos y-pos" + System.lineSeparator());
    appendMessage("set-filter layer-name filter-option" + System.lineSeparator());
    appendMessage("save-image file-name" + System.lineSeparator());
    appendMessage("quit or q" + System.lineSeparator());
  }
}

package controller;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import model.CollagerPPM;
import model.Layer;

/**
 * Represents an implementation of the collager controller interface. It accepts commands from
 * the users, passes it to the model to be processed, and outputs messages for users to view.
 */
public class CollagerControllerImpl implements CollagerController {
  private final Appendable appendable;

  /**
   * Constructor: Initializes the controller.
   *
   * @param appendable an appendable object for building messages.
   */
  public CollagerControllerImpl(Appendable appendable) {
    this.appendable = Objects.requireNonNull(appendable);
  }

  @Override
  public void run() throws IOException {
    Scanner sc = new Scanner(System.in);
    CollagerPPM currentCollager = new CollagerPPM();
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
          currentCollager.loadProject(path);
          appendMessage("Loaded project from " + path + ".\n");
          break;
        case "save-project":
          currentCollager.saveProject();
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
          if (!currentCollager.getLayers().contains(layerName)) {
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
          currentCollager.saveImage(fileName);
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
    appendMessage("save-project" + System.lineSeparator());
    appendMessage("add-layer layer-name" + System.lineSeparator());
    appendMessage("add-image-to-layer layer-name image-name x-pos y-pos" + System.lineSeparator());
    appendMessage("set-filter layer-name filter-option" + System.lineSeparator());
    appendMessage("save-image file-name" + System.lineSeparator());
    appendMessage("quit or q" + System.lineSeparator());
  }
}

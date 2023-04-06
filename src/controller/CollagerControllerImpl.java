package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.AbstractCollager;
import model.CollagerPPM;
import model.CollagerUtil;
import model.IFilter;
import model.ILayer;
import model.Layer;
import model.PixelColor;
import view.CollagerView;

/**
 * Represents an implementation of the collager controller interface. It accepts commands from
 * the users, passes it to the model to be processed, and outputs messages for users to view.
 */
public class CollagerControllerImpl implements CollagerController, ActionListener {
  private final Appendable appendable;
  private Readable input;
  private AbstractCollager currentCollager;
  private CollagerView view;
  private HashMap<String, ILayer> layers;

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

  /**
   * Constructor: Initializes the controller with a given view.
   *
   * @param appendable an appendable object for building messages.
   * @param view       a GUI view of the collager.
   */
  public CollagerControllerImpl(Appendable appendable, CollagerView view) {
    this.appendable = Objects.requireNonNull(appendable);
    this.view = Objects.requireNonNull(view);
    this.currentCollager = new CollagerPPM();
    this.layers = new HashMap<>();
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
          currentCollager = CollagerUtil.readCollager(path);
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
                  if (i == currentCollager.getLayers().size() - 1 &&
                          k == layer.getHeight() - 1 &&
                          j == layer.getWidth() - 1) {
                    file.write(pixel.getRed() + " " + pixel.getGreen() + " "
                            + pixel.getBlue() + " " + pixel.getAlpha());
                    break;
                  } else {
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
              layerOp = layer;
              break;
            }
          }
          if (!contains) {
            throw new IllegalArgumentException("Layer does not exist.");
          }
          if (filType.equals("difference") ||
                  filType.equals("multiply") ||
                  filType.equals("screen")) {
            layerOp.filterBlend(filType, currentCollager.getLayersBelow(layerOp.getName()));
          } else {
            try {
              layerOp.filter(filType);
            } catch (IllegalArgumentException e) {
              throw new IOException(e.getMessage());
            }
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
                if (i == currentCollager.getHeight() - 1 && k == currentCollager.getWidth() - 1) {
                  file.write(layerCurrent[i][k].getRed() + " " +
                          layerCurrent[i][k].getGreen() + " " +
                          layerCurrent[i][k].getBlue());
                } else {
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

  @Override
  public void runGUIMode() throws UnsupportedLookAndFeelException, ClassNotFoundException,
          InstantiationException, IllegalAccessException {
    this.view.initializeView();
    this.view.setActionListeners(this);
  }

  @Override
  public void runScriptMode(String filePath) {
    Scanner sc;
    StringBuilder builder = new StringBuilder();

    try {
      sc = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filePath + " not found!");
    }

    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    String input = builder.toString();

    new CollagerControllerImpl(this.appendable, new StringReader(input), this.currentCollager);
  }

  @Override
  public void actionPerformed(ActionEvent arg) {
    JFileChooser fileChooser;
    Object userPrompts;
    ILayer currentLayer = new Layer("bleh", 1, 1, 1);
    IFilter filter;

    switch (arg.getActionCommand()) {
      case "new-project":
        int height;
        int width;
        userPrompts = JOptionPane.showInputDialog(view.getMainPanel(),
                "Please enter height and width separated by a space:");
        // assign height and width values
        String prompt = (String) userPrompts;
        String[] prompts = prompt.split(" ");
        height = Integer.parseInt(prompts[0]);
        width = Integer.parseInt(prompts[1]);

        try {
          currentCollager.newProject(height, width);
        } catch (IllegalArgumentException e) {
          JOptionPane.showMessageDialog(view.getMainPanel(), "Invalid inputs.",
                  "Error!", JOptionPane.ERROR_MESSAGE);
        }
        currentLayer = currentCollager.getLayers().get(0);
        layers.put("currentLayer", currentLayer);
        view.updateComposite(currentLayer.convertToBuffered());
        break;
      case "open":
        break;
      case "save-image":
        break;
      case "save-project":
        break;
      case "add-image":
        fileChooser = new JFileChooser();
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter(
                "PPM", "ppm");
        fileChooser.setFileFilter(fileFilter);
        userPrompts = fileChooser.showOpenDialog(view.getMainPanel());
        currentLayer = layers.get("currentLayer");
        if ((int) userPrompts == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();

          try {
            currentCollager.addImageToLayer(currentLayer.getName(), file.getAbsolutePath(),
                    0, 0);
          } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(view.getMainPanel(), "Invalid action.",
                    "Error!", JOptionPane.ERROR_MESSAGE);
          }

          ILayer addedImage = null;
          try {
            addedImage = this.getColLayer(currentLayer);
          } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(view.getMainPanel(), "No such layer.",
                    "Error", JOptionPane.ERROR_MESSAGE);
          }
          layers.replace("currentLayer", addedImage);
          view.updateComposite(addedImage.convertToBuffered());
        } else {
          JOptionPane.showMessageDialog(view.getMainPanel(),
                  "File extension type not supported.",
                  "Error!", JOptionPane.ERROR_MESSAGE);
        }
        break;
      case "add-layer":
        break;
      case "col-component":
        String[] options = {"Red", "Green", "Blue"};
        userPrompts = JOptionPane.showOptionDialog(view.getMainPanel(),
                "What color components do you want to apply to the current layer?",
                "Color Components", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        currentLayer = layers.get("currentLayer");
        switch ((int) userPrompts) {
          case 0:
            try {
              currentCollager.setFilter(currentLayer.getName(), "red-component");
            } catch (IllegalArgumentException e) {
              JOptionPane.showMessageDialog(view.getMainPanel(), "Unexpected input.",
                      "Error!", JOptionPane.ERROR_MESSAGE);
            }
            break;
          case 1:
            try {
              currentCollager.setFilter(currentLayer.getName(), "green-component");
            } catch (IllegalArgumentException e) {
              JOptionPane.showMessageDialog(view.getMainPanel(), "Unexpected input.",
                      "Error!", JOptionPane.ERROR_MESSAGE);
            }
            break;
          case 2:
            try {
              currentCollager.setFilter(currentLayer.getName(), "blue-component");
            } catch (IllegalArgumentException e) {
              JOptionPane.showMessageDialog(view.getMainPanel(), "Unexpected input.",
                      "Error!", JOptionPane.ERROR_MESSAGE);
            }
            break;
          default:
            JOptionPane.showMessageDialog(view.getMainPanel(), "No selection were made.",
                    "Error!", JOptionPane.ERROR_MESSAGE);
            break;
        }
        ILayer colComponentImage = null;
        try {
          colComponentImage = this.getColLayer(currentLayer);
        } catch (IllegalArgumentException e) {
          JOptionPane.showMessageDialog(view.getMainPanel(), "No such layer.",
                  "Error", JOptionPane.ERROR_MESSAGE);
        }
        layers.replace("currentLayer", colComponentImage);
        view.updateComposite(colComponentImage.convertToBuffered());
        break;
      case "brighten":
        break;
      case "darken":
        break;
      default:
        break;
    }
  }

  /**
   * Checks if the given layer is in the collager, if so, return that layer.
   *
   * @param layer the given layer
   * @return the specified layer in the collager.
   */
  private Layer getColLayer(ILayer layer) throws IllegalArgumentException {
    for (int i = 0; i < currentCollager.getLayers().size(); i++) {
      if (currentCollager.getLayers().get(i).getName().equals(layer.getName())) {
        return currentCollager.getLayers().get(i);
      }
    }
    throw new IllegalArgumentException("Layer is not in the current collager.");
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

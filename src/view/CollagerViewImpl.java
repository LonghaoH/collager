package view;

import java.awt.BorderLayout;

import javax.swing.*;

/**
 * This class represents an implementation of the Collager view.
 */
public class CollagerViewImpl extends JFrame implements CollagerView {
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;
  private JMenuBar menuBar;
  private JMenu file;
  private JMenu edit;
  private JMenuItem fileNewProject;
  private JMenuItem fileOpen;
  private JMenuItem fileSave;
  private JMenuItem saveImage;
  private JMenuItem saveProject;
  private JMenuItem editAdd;
  private JMenuItem editSetFilter;
  private JMenuItem addImage;
  private JMenuItem addLayer;
  private JMenuItem normal;
  private JMenuItem colComponent;
  private JMenuItem brighten;
  private JMenuItem darken;
  private JMenuItem redCom;
  private JMenuItem greenCom;
  private JMenuItem blueCom;
  private JMenuItem value;
  private JMenuItem intensity;
  private JMenuItem luma;
  private JPanel imagePanel;

  @Override
  public void createMainFrame() throws UnsupportedLookAndFeelException, ClassNotFoundException,
          InstantiationException, IllegalAccessException {
    setTitle("Collager");
    setSize(800, 800);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout(mainPanel.getWidth(), mainPanel.getHeight()));
    //adds scroll bars around the main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);
  }

  @Override
  public void createMenuBar() {
    //create a menubar
    menuBar = new JMenuBar();
    file = new JMenu("File");
    edit = new JMenu("Edit");
    menuBar.add(file);
    menuBar.add(edit);

    //components for file menu
    fileNewProject = new JMenuItem("New Project");
    fileOpen = new JMenuItem("Open");
    fileSave = new JMenuItem("Save...");
    file.add(fileNewProject);
    file.add(fileOpen);
    file.add(fileSave);

    //components for the save menu item
    saveImage = new JMenuItem("Save Image");
    saveProject = new JMenuItem("Save Project");
    fileSave.add(saveImage);
    fileSave.add(saveProject);

    //components for edit menu
    editAdd = new JMenuItem("Add...");
    editSetFilter = new JMenuItem("Set Filter...");
    edit.add(editAdd);
    edit.add(editSetFilter);

    //components for the add menu item
    addImage = new JMenuItem("Add Image");
    addLayer = new JMenuItem("Add Layer");
    editAdd.add(addImage);
    editAdd.add(addLayer);

    //components for the set filter menu item
    normal = new JMenuItem("Normal");
    colComponent = new JMenuItem("Color Component...");
    brighten = new JMenuItem("Brighten...");
    darken = new JMenuItem("Darken...");
    editSetFilter.add(normal);
    editSetFilter.add(colComponent);
    editSetFilter.add(brighten);
    editSetFilter.add(darken);

    //components for the color components menu item
    redCom = new JMenuItem("Red");
    greenCom = new JMenuItem("Green");
    blueCom = new JMenuItem("Blue");
    colComponent.add(redCom);
    colComponent.add(greenCom);
    colComponent.add(blueCom);

    //components for the brighten and darken menu item
    value = new JMenuItem("Value");
    intensity = new JMenuItem("Intensity");
    luma = new JMenuItem("Luma");
    brighten.add(value);
    brighten.add(intensity);
    brighten.add(luma);
    darken.add(value);
    darken.add(intensity);
    darken.add(luma);
  }

  @Override
  public void createImagePanel() {
    //panel for displaying the current composite
    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Current composite:"));
    mainPanel.add(imagePanel, BorderLayout.LINE_START);
  }
}

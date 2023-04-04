package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UnsupportedLookAndFeelException;

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
  private JLabel imageLabel;

  @Override
  public void createMainFrame() throws UnsupportedLookAndFeelException, ClassNotFoundException,
          InstantiationException, IllegalAccessException {
    setTitle("Collager");
    setSize(800, 600);

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

    mainPanel.add(menuBar, BorderLayout.NORTH);
  }

  @Override
  public void createImagePanel() {
    //panel for displaying the current composite
    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Current composite:"));
    mainPanel.add(imagePanel, BorderLayout.CENTER);

    imageLabel = new JLabel();
    JScrollPane scrollPane = new JScrollPane(imageLabel);
    scrollPane.setPreferredSize(new Dimension(500, 500));
    imagePanel.add(imageLabel);
  }

  @Override
  public void initializeView() throws UnsupportedLookAndFeelException, ClassNotFoundException,
          InstantiationException, IllegalAccessException {
    this.createMainFrame();
    this.createMenuBar();
    this.createImagePanel();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void setActionListeners(ActionListener listener) {
    fileNewProject.setActionCommand("new-project");
    fileNewProject.addActionListener(listener);

    fileOpen.setActionCommand("load-project");
    fileOpen.addActionListener(listener);

    saveImage.setActionCommand("save-image");
    saveImage.addActionListener(listener);

    saveProject.setActionCommand("save-project");
    saveProject.addActionListener(listener);

    addImage.setActionCommand("add-image-to-layer");
    addImage.addActionListener(listener);

    addLayer.setActionCommand("add-layer");
    addLayer.addActionListener(listener);

    redCom.setActionCommand("set-filter");
    redCom.addActionListener(listener);
    greenCom.setActionCommand("set-filter");
    greenCom.addActionListener(listener);
    blueCom.setActionCommand("set-filter");
    blueCom.addActionListener(listener);

    value.setActionCommand("set-filter");
    value.addActionListener(listener);
    value.setActionCommand("set-filter");
    value.addActionListener(listener);
    value.setActionCommand("set-filter");
    value.addActionListener(listener);
  }

  /**
   * Main method to run this view implementation.
   * Just a helper method to test if view runs and works as intended.
   */
  public static void main(String[] args) throws UnsupportedLookAndFeelException,
          ClassNotFoundException, InstantiationException, IllegalAccessException {
    CollagerViewImpl testView = new CollagerViewImpl();
    testView.initializeView();
    testView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    testView.setVisible(true);
  }
}

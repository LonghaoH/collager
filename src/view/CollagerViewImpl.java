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
  private JMenuItem fileSaveImage;
  private JMenuItem fileSaveProject;
  private JMenuItem editAddImage;
  private JMenuItem editAddLayer;
  private JMenuItem editColComponent;
  private JMenuItem editBrighten;
  private JMenuItem editDarken;
  private JPanel imagePanel;
  private JLabel imageLabel;
  private JPanel layerPanel;
  private JLabel layerLabel;

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
    fileSaveImage = new JMenuItem("Save Image");
    fileSaveProject = new JMenuItem("Save Project");
    file.add(fileNewProject);
    file.add(fileOpen);
    file.add(fileSaveImage);
    file.add(fileSaveProject);

    //components for edit menu
    editAddImage = new JMenuItem("Add Image");
    editAddLayer = new JMenuItem("Add Layer");
    editColComponent = new JMenuItem("Color Components");
    editBrighten = new JMenuItem("Brighten");
    editDarken = new JMenuItem("Darken");
    edit.add(editAddImage);
    edit.add(editAddLayer);
    edit.add(editColComponent);
    edit.add(editBrighten);
    edit.add(editDarken);

    mainPanel.add(menuBar, BorderLayout.NORTH);
  }

  @Override
  public void createImagePanel() {
    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Current composite:"));
    mainPanel.add(imagePanel, BorderLayout.CENTER);

    imageLabel = new JLabel();
    JScrollPane scrollPane = new JScrollPane(imageLabel);
    scrollPane.setPreferredSize(new Dimension(500, 500));
    imagePanel.add(imageLabel);
  }

  @Override
  public void createLayerPanel() {
    layerPanel = new JPanel();
    layerPanel.setBorder(BorderFactory.createTitledBorder("Layers:"));
    mainPanel.add(layerPanel, BorderLayout.EAST);

    layerLabel = new JLabel();
    JScrollPane scrollPane = new JScrollPane(layerLabel);
    scrollPane.setPreferredSize(new Dimension(500, 500));
    layerPanel.add(layerLabel);
  }

  @Override
  public void initializeView() throws UnsupportedLookAndFeelException, ClassNotFoundException,
          InstantiationException, IllegalAccessException {
    this.createMainFrame();
    this.createMenuBar();
    this.createImagePanel();
    this.createLayerPanel();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void setActionListeners(ActionListener listener) {
    fileNewProject.setActionCommand("new-project");
    fileNewProject.addActionListener(listener);

    fileOpen.setActionCommand("open");
    fileOpen.addActionListener(listener);

    fileSaveImage.setActionCommand("save-image");
    fileSaveImage.addActionListener(listener);

    fileSaveProject.setActionCommand("save-project");
    fileSaveProject.addActionListener(listener);

    editAddImage.setActionCommand("add-image");
    editAddImage.addActionListener(listener);

    editAddLayer.setActionCommand("add-layer");
    editAddLayer.addActionListener(listener);

    editColComponent.setActionCommand("col-component");
    editColComponent.addActionListener(listener);

    editBrighten.setActionCommand("brighten");
    editBrighten.addActionListener(listener);

    editDarken.setActionCommand("darken");
    editDarken.addActionListener(listener);
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

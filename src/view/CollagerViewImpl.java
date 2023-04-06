package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
  private JMenuItem fileNewProject;
  private JMenuItem fileOpen;
  private JMenuItem fileSaveImage;
  private JMenuItem fileSaveProject;
  private JMenuItem editAddImage;
  private JMenuItem editAddLayer;
  private JMenuItem editColComponent;
  private JMenuItem editBrighten;
  private JMenuItem editDarken;
  private JPanel layerPanel;
  private JLabel imageLabel;
  private JButton newLayer;

  @Override
  public void createMainFrame() throws UnsupportedLookAndFeelException, ClassNotFoundException,
          InstantiationException, IllegalAccessException {
    setTitle("Collager");
    setSize(1000, 800);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout(mainPanel.getWidth(), mainPanel.getHeight()));
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);
  }

  @Override
  public void createMenuBar() {
    //create a menubar
    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu edit = new JMenu("Edit");
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
    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Current composite:"));
    imageLabel = new JLabel();
    JScrollPane scrollPane = new JScrollPane(imageLabel);
    scrollPane.setPreferredSize(new Dimension(900, 600));
    imagePanel.add(scrollPane);

    mainPanel.add(imagePanel, BorderLayout.CENTER);
  }

  @Override
  public void createLayerPanel() {
    layerPanel = new JPanel();
    layerPanel.setBorder(BorderFactory.createTitledBorder("Layers:"));
    JScrollPane scrollPane = new JScrollPane(newLayer);
    scrollPane.setPreferredSize(new Dimension(0, 50));
    layerPanel.add(scrollPane);

    mainPanel.add(layerPanel, BorderLayout.SOUTH);
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
  public JPanel getMainPanel() {
    return this.mainPanel;
  }

  @Override
  public void updateComposite(Image image) {
    this.imageLabel.setIcon(new ImageIcon(Objects.requireNonNull(image)));
  }

  @Override
  public void updateLayer(String layerName) {
    newLayer = new JButton(layerName);
    this.layerPanel.add(newLayer);
    this.layerPanel.repaint();
    this.layerPanel.revalidate();
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

    newLayer.setActionCommand("set-current-layer");
    newLayer.addActionListener(listener);
  }

  @Override
  public void refresh() {
    revalidate();
    repaint();
  }
}

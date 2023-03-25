package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
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
  private JMenuItem fileSaveAs;

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
    fileSaveImage = new JMenuItem("Save Image");
    fileSaveAs = new JMenuItem("Save As");
    file.add(fileNewProject);
    file.add(fileOpen);
    file.add(fileSaveImage);
    file.add(fileSaveAs);
  }

  @Override
  public void createImagePanel() {

  }
}

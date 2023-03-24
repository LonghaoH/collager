package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import model.AbstractCollager;
import model.CollagerPPM;

import static org.junit.Assert.fail;

/**
 * Tests the file writing and file saving methods of the controller, save-image, save-project,
 * load-project.
 */
public class ControllerFileTests {
  AbstractCollager collager;
  CollagerController controller;
  Readable in;
  Appendable throwaway = new StringBuilder();
  @Before
  public void setUp() {
    collager = new CollagerPPM();
  }

  @Test
  public void testSaveProject() {
    this.setUp();
    controller = new CollagerControllerImpl(throwaway, new StringReader("new-project 5 5\n" +
            "save-project\n"));
    try {
      controller.run();

      //tests to see if the project file was properly created
      Scanner sc = null;
      StringBuilder builder = new StringBuilder();
      try {
        sc = new Scanner(new FileInputStream
                ("C:\\Users\\jdhoo\\Documents\\IntelliJ\\Collager\\Project"));
      } catch (FileNotFoundException e) {
        fail("file not created properly");
      }
      
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        builder.append(s + System.lineSeparator());
      }
    } catch (IOException e) {
      //idk
    }
  }
}

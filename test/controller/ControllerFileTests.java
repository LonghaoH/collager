package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import model.AbstractCollager;
import model.CollagerPPM;
import model.CollagerUtil;
import model.PixelColor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
            "save-project\n"), collager);
    try {
      controller.run();

      //tests to see if the file was created properly
      File file = new File("C:\\Users\\jdhoo\\Documents\\GitHub\\collager\\Project");
      assertTrue(file.exists());

      Scanner sc = new Scanner(new FileInputStream
              ("C:\\Users\\jdhoo\\Documents\\GitHub\\collager\\Project"));
      StringBuilder builder = new StringBuilder();
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        builder.append(s + System.lineSeparator());
      }
      sc = new Scanner(builder.toString());

      //tests that the contents of the file are correct
      String token = sc.next();
      int width = sc.nextInt();
      int height = sc.nextInt();
      int maxVal = sc.nextInt();
      String layerName = sc.next();
      String filterName = sc.next();

      assertEquals("C1", token);
      assertEquals(5, width);
      assertEquals(5, height);
      assertEquals(255, maxVal);
      assertEquals("background", layerName);
      assertEquals("normal", filterName);
      for (int i = 0; i < collager.getHeight(); i++) {
        for (int k = 0; k < collager.getWidth(); k++) {
          assertEquals(collager.getLayers().get(0).getLayerImage()[i][k].getRed(), sc.nextInt());
          assertEquals(collager.getLayers().get(0).getLayerImage()[i][k].getGreen(), sc.nextInt());
          assertEquals(collager.getLayers().get(0).getLayerImage()[i][k].getBlue(), sc.nextInt());
        }
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("invalid input");
    }
  }

  @Test
  public void testLoadProject() {
    this.setUp();
    controller = new CollagerControllerImpl(throwaway, new StringReader(
            "load-project C:\\Users\\jdhoo\\Documents\\GitHub\\collager\\Project"), collager);

    try {
      assertEquals(0, collager.getWidth());
      assertEquals(0, collager.getWidth());
      assertEquals(255, collager.getMaxVal());
      assertEquals(0, collager.getLayers().size());

      controller.run();

      assertEquals(5, this.collager.getWidth());
      assertEquals(5, this.collager.getWidth());
      assertEquals(255, this.collager.getMaxVal());
      assertEquals(1, this.collager.getLayers().size());
      assertEquals("background", this.collager.getLayers().get(0).getName());
    } catch (IOException e) {
      throw new IllegalArgumentException("invalid input");
    }
  }

  @Test
  public void testSaveImage() {
    this.setUp();
    controller = new CollagerControllerImpl(throwaway, new StringReader(
            "new-project 100 100 " +
                    "add-layer purple " +
                    "add-image-to-layer purple " +
                    "C:\\Users\\jdhoo\\Documents\\GitHub\\collager\\utils\\purple50x50.ppm 0 0 " +
                    "save-image " +
                    "C:\\Users\\jdhoo\\Documents\\GitHub\\collager\\utils\\purple100x100.ppm")
            , collager);

    //tests to see if the file was created properly
    File file = new File
            ("C:\\Users\\jdhoo\\Documents\\GitHub\\collager\\utils\\purple100x100.ppm");
    file.delete();
    assertFalse(file.exists());

    try {
      controller.run();

      assertTrue(file.exists());

      PixelColor[][] image = CollagerUtil.readPPM(
              "C:\\Users\\jdhoo\\Documents\\GitHub\\collager\\utils\\purple100x100.ppm");

      assertEquals(100,
              CollagerUtil.getHeight(
                      "C:\\Users\\jdhoo\\Documents\\GitHub\\" +
                              "collager\\utils\\purple100x100.ppm"));

      assertEquals(100,
              CollagerUtil.getWidth(
                      "C:\\Users\\jdhoo\\Documents\\GitHub\\" +
                              "collager\\utils\\purple100x100.ppm"));

      //tests the image saves properly
      for (int i = 0; i < 50; i++) {
        for (int k = 0; k < 50; k++) {
          assertEquals(168, image[i][k].getRed());
          assertEquals(91, image[i][k].getGreen());
          assertEquals(188, image[i][k].getBlue());
          assertEquals(255, image[i][k].getAlpha());
        }
      }

    } catch (IOException e) {
      throw new IllegalArgumentException("invalid input.");
    }
  }
}

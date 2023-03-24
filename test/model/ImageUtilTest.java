package model;

import org.junit.Test;

import static model.ImageUtil.readCollager;
import static model.ImageUtil.readPPM;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ImageUtilTest {
  @Test
  public void testReadPPM() {
    try {
      readPPM("hi");
      fail("shouldn't work if file does not exist");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
    PixelColor[][] imageTest = new PixelColor[50][50];
    PixelColor[][] image = new PixelColor[50][50];

    for(int i = 0; i < 50; i++) {
      for(int k = 0; k < 50; k++) {
        imageTest[i][k] = new PixelColor(168, 91, 188);
      }
    }

    image = ImageUtil.readPPM(
            "C:\\Users\\jdhoo\\Documents\\GitHub\\collager\\utils\\purple50x50.ppm");

    for(int i = 0; i < 50; i++) {
      for(int k = 0; k < 50; k++) {
        assertEquals(image[i][k].getRed(), imageTest[i][k].getRed());
        assertEquals(image[i][k].getGreen(), imageTest[i][k].getGreen());
        assertEquals(image[i][k].getBlue(), imageTest[i][k].getBlue());
      }
    }
  }

  /**
   * Tests that the getWidth, and getHeight method work for the ImageUtil class.
   */
  @Test
  public void testGetDimensions() {
    assertEquals(50, ImageUtil.getWidth(
            "C:\\Users\\jdhoo\\Documents\\GitHub\\collager\\utils\\purple50x50.ppm"));
    assertEquals(50, ImageUtil.getHeight(
            "C:\\Users\\jdhoo\\Documents\\GitHub\\collager\\utils\\purple50x50.ppm"));
  }

  @Test
  public void testReadCollager() {
    AbstractCollager collager = new CollagerPPM();
    assertEquals(0, collager.getWidth());
    assertEquals(0, collager.getHeight());
    assertEquals(255, collager.getMaxVal());
    assertEquals(0, collager.getLayers().size());

    collager =
            readCollager("C:\\Users\\jdhoo\\Documents\\GitHub\\collager\\Project");

    assertEquals(5, collager.getWidth());
    assertEquals(5, collager.getHeight());
    assertEquals(255, collager.getMaxVal());
    assertEquals(1, collager.getLayers().size());
  }
}

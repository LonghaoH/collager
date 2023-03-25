package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the FilterImpl class.
 */
public class FilterImplTest {
  @Test
  public void testConstructor() {
    try {
      FilterImpl filter = new FilterImpl(null);
    } catch (IllegalArgumentException e) {
      //do nothing
    }
  }

  @Test
  public void testApplyFilter() {
    PixelColor pix = new PixelColor(5, 5, 5);

    FilterImpl filter = new FilterImpl(pix);

    filter.applyFilter("red-component");
    assertEquals(5, pix.getRed());
    assertEquals(0, pix.getGreen());
    assertEquals(0, pix.getBlue());

    pix = new PixelColor(5, 5, 5);
    filter = new FilterImpl(pix);
    filter.applyFilter("green-component");
    assertEquals(0, pix.getRed());
    assertEquals(5, pix.getGreen());
    assertEquals(0, pix.getBlue());

    pix = new PixelColor(5, 5, 5);
    filter = new FilterImpl(pix);
    filter.applyFilter("blue-component");
    assertEquals(0, pix.getRed());
    assertEquals(0, pix.getGreen());
    assertEquals(5, pix.getBlue());

    pix = new PixelColor(5, 5, 5);
    filter = new FilterImpl(pix);
    filter.applyFilter("brighten-value");
    assertEquals(10, pix.getRed());
    assertEquals(10, pix.getGreen());
    assertEquals(10, pix.getBlue());

    pix = new PixelColor(5, 5, 5);
    filter = new FilterImpl(pix);
    filter.applyFilter("brighten-intensity");
    assertEquals(10, pix.getRed());
    assertEquals(10, pix.getGreen());
    assertEquals(10, pix.getBlue());

    pix = new PixelColor(5, 5, 5);
    filter = new FilterImpl(pix);
    filter.applyFilter("brighten-luma");
    assertEquals(10, pix.getRed());
    assertEquals(10, pix.getGreen());
    assertEquals(10, pix.getBlue());

    pix = new PixelColor(5, 5, 5);
    filter = new FilterImpl(pix);
    filter.applyFilter("darken-value");
    assertEquals(0, pix.getRed());
    assertEquals(0, pix.getGreen());
    assertEquals(0, pix.getBlue());

    pix = new PixelColor(5, 5, 5);
    filter = new FilterImpl(pix);
    filter.applyFilter("darken-intensity");
    assertEquals(0, pix.getRed());
    assertEquals(0, pix.getGreen());
    assertEquals(0, pix.getBlue());

    pix = new PixelColor(5, 5, 5);
    filter = new FilterImpl(pix);
    filter.applyFilter("darken-luma");
    assertEquals(0, pix.getRed());
    assertEquals(0, pix.getGreen());
    assertEquals(0, pix.getBlue());
  }

  @Test
  public void testApplyBlendFilter() {
    PixelColor pix = new PixelColor(5, 5, 5);
    PixelColor compWhite = new PixelColor(255, 255, 255);
    PixelColor compBlack = new PixelColor(0, 0, 0);

    FilterImpl filter = new FilterImpl(pix);

    filter.applyBlendFilter("difference", compWhite);
    assertEquals(250, pix.getRed());
    assertEquals(250, pix.getGreen());
    assertEquals(250, pix.getBlue());

    pix = new PixelColor(5, 5, 5);
    filter = new FilterImpl(pix);
    filter.applyBlendFilter("multiply", compBlack);
    assertEquals(0, pix.getRed());
    assertEquals(0, pix.getGreen());
    assertEquals(0, pix.getBlue());

    pix = new PixelColor(5, 5, 5);
    filter = new FilterImpl(pix);
    filter.applyBlendFilter("screen", compWhite);
    assertEquals(255, pix.getRed());
    assertEquals(255, pix.getGreen());
    assertEquals(255, pix.getBlue());
  }
}

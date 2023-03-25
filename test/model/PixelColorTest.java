package model;

import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the PixelColor class.
 */
public class PixelColorTest {
  PixelColor pc1;
  PixelColor pc2;
  PixelColor pc3;
  PixelColor pc4;
  PixelColor pc5;
  PixelColor pc6;

  @Before
  public void setUp() throws Exception {
    pc1 = new PixelColor(255, 255, 255, 255);
    pc2 = new PixelColor(0, 0, 0, 255);
    pc3 = new PixelColor(128, 128, 0, 128);
    pc4 = new PixelColor(0, 128, 0, 128);
    pc5 = new PixelColor(128, 128, 128, 0);
    pc6 = new PixelColor(64, 128, 255);
  }

  @Test
  public void testConstructorException() {
    try { // negative red component with 3-components pixel
      PixelColor negRed = new PixelColor(-20, 18, 155);
    } catch (IllegalArgumentException e) {
      assertEquals("RGB values cannot be negative.", e.getMessage());
    }

    try { // negative green component with 3-components pixel
      PixelColor negGreen = new PixelColor(20, -18, 155);
    } catch (IllegalArgumentException e) {
      assertEquals("RGB values cannot be negative.", e.getMessage());
    }

    try { // negative blue component with 3-component pixel
      PixelColor negBlue = new PixelColor(20, 18, -155);
    } catch (IllegalArgumentException e) {
      assertEquals("RGB values cannot be negative.", e.getMessage());
    }

    try { // negative red component with 4-components pixel
      PixelColor negRed = new PixelColor(-20, 18, 155, 34);
    } catch (IllegalArgumentException e) {
      assertEquals("Alpha and RGB values cannot be negative.", e.getMessage());
    }

    try { // negative green component with 4-components pixel
      PixelColor negGreen = new PixelColor(20, -18, 155, 34);
    } catch (IllegalArgumentException e) {
      assertEquals("Alpha and RGB values cannot be negative.", e.getMessage());
    }

    try { // negative blue component with 4-component pixel
      PixelColor negBlue = new PixelColor(20, 18, -155, 34);
    } catch (IllegalArgumentException e) {
      assertEquals("Alpha and RGB values cannot be negative.", e.getMessage());
    }

    try { // negative alpha value with 4-component pixel
      PixelColor negAlpha = new PixelColor(20, 18, 155, -34);
    } catch (IllegalArgumentException e) {
      assertEquals("Alpha and RGB values cannot be negative.", e.getMessage());
    }
  }

  @Test
  public void layerColor() throws Exception {
    this.setUp();
    // test layering a fully opaque pixel to a half opaque pixel
    PixelColor ePc1 = pc1.layerColor(128, 128, 128, 128);
    assertEquals(255, ePc1.getRed());
    assertEquals(255, ePc1.getGreen());
    assertEquals(255, ePc1.getBlue());
    assertEquals(255, ePc1.getAlpha());

    // test layering a half opaque pixel to another half opaque pixel
    PixelColor ePc3 = pc3.layerColor(128, 128, 128, 128);
    assertEquals(106, ePc3.getRed());
    assertEquals(106, ePc3.getGreen());
    assertEquals(42, ePc3.getBlue());
    assertEquals(191, ePc3.getAlpha());

    // test layering a half opaque pixel to a fully opaque pixel
    PixelColor ePc4 = pc4.layerColor(128, 128, 128, 255);
    assertEquals(63, ePc4.getRed());
    assertEquals(128, ePc4.getGreen());
    assertEquals(63, ePc4.getBlue());
    assertEquals(255, ePc4.getAlpha());

    // test layering a transparent pixel to a fully opaque pixel
    PixelColor ePc5 = pc5.layerColor(128, 128, 128, 255);
    assertEquals(128, ePc5.getRed());
    assertEquals(128, ePc5.getGreen());
    assertEquals(128, ePc5.getBlue());
    assertEquals(255, ePc5.getAlpha());

    // test layering a transparent pixel to a half opaque pixel
    PixelColor exPc5 = pc5.layerColor(128, 128, 128, 128);
    assertEquals(128, exPc5.getRed());
    assertEquals(128, exPc5.getGreen());
    assertEquals(128, exPc5.getBlue());
    assertEquals(128, exPc5.getAlpha());

    // test layering a transparent pixel to another transparent pixel
    PixelColor expPc5 = pc5.layerColor(128, 128, 128, 0);
    assertEquals(0, expPc5.getRed());
    assertEquals(0, expPc5.getGreen());
    assertEquals(0, expPc5.getBlue());
    assertEquals(0, expPc5.getAlpha());
  }

  @Test
  public void convertTo3Components() throws Exception {
    this.setUp();
    // test converting a full opacity 4-components pixel to 3-components pixel
    PixelColor ePc1 = pc1.convertTo3Components();
    assertEquals(255, ePc1.getRed());
    assertEquals(255, ePc1.getGreen());
    assertEquals(255, ePc1.getBlue());

    // test converting a half opaque 4-components pixel to 3-components pixel
    PixelColor ePc3 = pc3.convertTo3Components();
    assertEquals(64, ePc3.getRed());
    assertEquals(64, ePc3.getGreen());
    assertEquals(0, ePc3.getBlue());

    // test converting a fully transparent 4-components pixel to 3-components pixel
    PixelColor ePc5 = pc5.convertTo3Components();
    assertEquals(0, ePc5.getRed());
    assertEquals(0, ePc5.getGreen());
    assertEquals(0, ePc5.getBlue());
  }

  @Test
  public void testColComponent() throws Exception {
    this.setUp();
    try {
      pc2.colComponent("hi");
      fail("Should not be able to initialize with unsupported color");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
    pc1.colComponent("red");
    assertEquals(255, pc1.getRed());
    assertEquals(0, pc1.getGreen());
    assertEquals(0, pc1.getBlue());
    pc3.colComponent("red");
    assertEquals(128, pc3.getRed());
    assertEquals(0, pc3.getGreen());
    assertEquals(0, pc3.getBlue());

    pc5.colComponent("green");
    assertEquals(0, pc5.getRed());
    assertEquals(128, pc5.getGreen());
    assertEquals(0, pc5.getBlue());
    pc2.colComponent("green");
    assertEquals(0, pc2.getRed());
    assertEquals(0, pc2.getGreen());
    assertEquals(0, pc2.getBlue());

    pc6.colComponent("blue");
    assertEquals(0, pc6.getRed());
    assertEquals(0, pc6.getGreen());
    assertEquals(255, pc6.getBlue());
    pc2.colComponent("blue");
    assertEquals(0, pc2.getRed());
    assertEquals(0, pc2.getGreen());
    assertEquals(0, pc2.getBlue());
  }

  @Test
  public void testBrighten() throws Exception {
    this.setUp();
    try {
      pc1.brighten("BRIGHTEN");
      fail("Should not be able to use brighten with unknown type");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
    assertEquals(255, pc1.getRed());
    assertEquals(255, pc1.getGreen());
    assertEquals(255, pc1.getBlue());
    pc1.brighten("value");
    assertEquals(255, pc1.getRed());
    assertEquals(255, pc1.getGreen());
    assertEquals(255, pc1.getBlue());

    assertEquals(128, pc5.getRed());
    assertEquals(128, pc5.getGreen());
    assertEquals(128, pc5.getBlue());
    pc5.brighten("value");
    assertEquals(255, pc5.getRed());
    assertEquals(255, pc5.getGreen());
    assertEquals(255, pc5.getBlue());

    this.setUp();

    assertEquals(255, pc1.getRed());
    assertEquals(255, pc1.getGreen());
    assertEquals(255, pc1.getBlue());
    pc1.brighten("intensity");
    assertEquals(255, pc1.getRed());
    assertEquals(255, pc1.getGreen());
    assertEquals(255, pc1.getBlue());

    assertEquals(128, pc5.getRed());
    assertEquals(128, pc5.getGreen());
    assertEquals(128, pc5.getBlue());
    pc5.brighten("intensity");
    assertEquals(255, pc5.getRed());
    assertEquals(255, pc5.getGreen());
    assertEquals(255, pc5.getBlue());

    this.setUp();

    assertEquals(255, pc1.getRed());
    assertEquals(255, pc1.getGreen());
    assertEquals(255, pc1.getBlue());
    pc1.brighten("luma");
    assertEquals(255, pc1.getRed());
    assertEquals(255, pc1.getGreen());
    assertEquals(255, pc1.getBlue());

    assertEquals(128, pc5.getRed());
    assertEquals(128, pc5.getGreen());
    assertEquals(128, pc5.getBlue());
    pc5.brighten("luma");
    assertEquals(255, pc5.getRed());
    assertEquals(255, pc5.getGreen());
    assertEquals(255, pc5.getBlue());
  }

  @Test
  public void testDarken() throws Exception {
    this.setUp();
    try {
      pc1.darken("DARKEN");
      fail("Should not be able to use darken with unknown type");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
    assertEquals(0, pc2.getRed());
    assertEquals(0, pc2.getGreen());
    assertEquals(0, pc2.getBlue());
    pc2.darken("value");
    assertEquals(0, pc2.getRed());
    assertEquals(0, pc2.getGreen());
    assertEquals(0, pc2.getBlue());

    assertEquals(128, pc5.getRed());
    assertEquals(128, pc5.getGreen());
    assertEquals(128, pc5.getBlue());
    pc5.darken("value");
    assertEquals(0, pc5.getRed());
    assertEquals(0, pc5.getGreen());
    assertEquals(0, pc5.getBlue());

    this.setUp();

    assertEquals(0, pc2.getRed());
    assertEquals(0, pc2.getGreen());
    assertEquals(0, pc2.getBlue());
    pc2.darken("intensity");
    assertEquals(0, pc2.getRed());
    assertEquals(0, pc2.getGreen());
    assertEquals(0, pc2.getBlue());

    assertEquals(128, pc5.getRed());
    assertEquals(128, pc5.getGreen());
    assertEquals(128, pc5.getBlue());
    pc5.darken("intensity");
    assertEquals(0, pc5.getRed());
    assertEquals(0, pc5.getGreen());
    assertEquals(0, pc5.getBlue());

    this.setUp();

    assertEquals(0, pc2.getRed());
    assertEquals(0, pc2.getGreen());
    assertEquals(0, pc2.getBlue());
    pc2.darken("luma");
    assertEquals(0, pc2.getRed());
    assertEquals(0, pc2.getGreen());
    assertEquals(0, pc2.getBlue());

    assertEquals(128, pc5.getRed());
    assertEquals(128, pc5.getGreen());
    assertEquals(128, pc5.getBlue());
    pc5.darken("luma");
    assertEquals(0, pc5.getRed());
    assertEquals(0, pc5.getGreen());
    assertEquals(0, pc5.getBlue());
  }

  @Test
  public void testDifference() throws Exception {
    this.setUp();

    pc5.difference(pc1);
    assertEquals(127, pc5.getRed());
    assertEquals(127, pc5.getGreen());
    assertEquals(127, pc5.getBlue());

    this.setUp();

    pc5.difference(pc2);
    assertEquals(128, pc5.getRed());
    assertEquals(128, pc5.getRed());
    assertEquals(128, pc5.getRed());
  }

  @Test
  public void testMultiply() throws Exception {
    this.setUp();

    pc5.multiply(pc1);
    assertEquals(128, pc5.getRed());
    assertEquals(128, pc5.getGreen());
    assertEquals(128, pc5.getBlue());

    this.setUp();

    pc5.multiply(pc2);
    assertEquals(0, pc5.getRed());
    assertEquals(0, pc5.getRed());
    assertEquals(0, pc5.getRed());
  }

  @Test
  public void testScreen() throws Exception {
    this.setUp();

    pc5.screen(pc1);
    assertEquals(255, pc5.getRed());
    assertEquals(255, pc5.getGreen());
    assertEquals(255, pc5.getBlue());

    this.setUp();

    pc5.screen(pc2);
    assertEquals(128, pc5.getRed());
    assertEquals(128, pc5.getRed());
    assertEquals(128, pc5.getRed());
  }

  @Test
  public void getRed() throws Exception {
    this.setUp();
    assertEquals(255, pc1.getRed());
    assertEquals(0, pc2.getRed());
    assertEquals(128, pc3.getRed());
    assertEquals(0, pc4.getRed());
    assertEquals(128, pc5.getRed());
    assertEquals(64, pc6.getRed());
  }

  @Test
  public void getGreen() throws Exception {
    this.setUp();
    assertEquals(255, pc1.getGreen());
    assertEquals(0, pc2.getGreen());
    assertEquals(128, pc3.getGreen());
    assertEquals(128, pc4.getGreen());
    assertEquals(128, pc5.getGreen());
    assertEquals(128, pc6.getGreen());
  }

  @Test
  public void getBlue() throws Exception {
    this.setUp();
    assertEquals(255, pc1.getBlue());
    assertEquals(0, pc2.getBlue());
    assertEquals(0, pc3.getBlue());
    assertEquals(0, pc4.getBlue());
    assertEquals(128, pc5.getBlue());
    assertEquals(255, pc6.getBlue());
  }

  @Test
  public void getAlpha() throws Exception {
    this.setUp();
    assertEquals(255, pc1.getAlpha());
    assertEquals(255, pc2.getAlpha());
    assertEquals(128, pc3.getAlpha());
    assertEquals(128, pc4.getAlpha());
    assertEquals(0, pc5.getAlpha());
    assertEquals(255, pc6.getAlpha());
  }

  @Test
  public void testEquals() throws Exception {
    this.setUp();
    assertFalse(pc4.equals(pc1));
    assertFalse(Objects.isNull(pc4));
    assertFalse(pc4.equals(new PixelColor(1, 128, 0, 128)));
    assertFalse(pc4.equals(new PixelColor(0, 127, 0, 128)));
    assertFalse(pc4.equals(new PixelColor(0, 128, 2, 128)));
    assertFalse(pc4.equals(new PixelColor(0, 128, 0, 110)));
    assertTrue(pc4.equals(new PixelColor(0, 128, 0, 128)));
  }
}
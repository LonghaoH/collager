package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Tests the Layer class.
 */
public class LayerTest {
  /**
   * Tests the valid and invalid construction of the Layer class.
   */
  @Test
  public void testValidConstruction() {
    Layer layer;
    try {
      layer = new Layer(null, 0, 0, 5);
      fail("Should not be able to initialize with null name");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
    try {
      layer = new Layer("Hi", -1, 0, 5);
      fail("Should not be able to initialize with negative height");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
    try {
      layer = new Layer("Hi", 0, -1, 5);
      fail("Should not be able to initialize with negative width");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
    try {
      layer = new Layer("Hi", 0, 0, -1);
      fail("Should not be able to initialize with negative maximum value");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
    layer = new Layer("Layer1", 500, 500, 255);
    assertEquals("Layer1", layer.getName());
    assertEquals(500, layer.getHeight());
    assertEquals(500, layer.getWidth());
    assertEquals(255, layer.getMaxVal());
  }

  @Test
  public void testGetHeight() {
    Layer layer = new Layer("Layer1", 500, 500, 255);
    assertEquals(500, layer.getHeight());
  }

  @Test
  public void testGetWidth() {
    Layer layer = new Layer("Layer1", 500, 500, 255);
    assertEquals(500, layer.getWidth());
  }

  @Test
  public void testGetMaxVal() {
    Layer layer = new Layer("Layer1", 500, 500, 255);
    assertEquals(255, layer.getMaxVal());
  }

  @Test
  public void testGetLayerImage() {
    Layer layer = new Layer("Layer1", 500, 500, 255);
    assertEquals(500, layer.getLayerImage().length);
    try {
      for (int i = 0; i < layer.getHeight(); i++) {
        for (int k = 0; k < layer.getWidth(); k++) {
          assertNull(layer.getLayerImage()[i][k]);
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      fail("layer did not initialize image properly");
    }
  }

  @Test
  public void testWhiteLayer() {
    Layer layer = new Layer("Layer1", 500, 500, 255);
    for (int i = 0; i < layer.getHeight(); i++) {
      for (int k = 0; k < layer.getWidth(); k++) {
        assertNull(layer.getLayerImage()[i][k]);
      }
    }
    layer.whiteLayer();
    for (int i = 0; i < layer.getHeight(); i++) {
      for (int k = 0; k < layer.getWidth(); k++) {
        assertEquals(new PixelColor(255, 255, 255), layer.getLayerImage()[i][k]);
      }
    }
  }

  @Test
  public void testDefaultLayer() {
    Layer layer = new Layer("Layer1", 500, 500, 255);
    for (int i = 0; i < layer.getHeight(); i++) {
      for (int k = 0; k < layer.getWidth(); k++) {
        assertNull(layer.getLayerImage()[i][k]);
      }
    }
    layer.defaultLayer();
    for (int i = 0; i < layer.getHeight(); i++) {
      for (int k = 0; k < layer.getWidth(); k++) {
        assertEquals(new PixelColor(255, 255, 255, 0), layer.getLayerImage()[i][k]);
      }
    }
  }

  @Test
  public void testAddImage() {
    Layer layer = new Layer("Layer1", 100, 100, 255);
    layer.defaultLayer();
    for (int i = 0; i < layer.getHeight(); i++) {
      for (int k = 0; k < layer.getWidth(); k++) {
        assertEquals(new PixelColor(255, 255, 255, 0), layer.getLayerImage()[i][k]);
      }
    }
    layer.addImage(
            "C:\\Users\\jdhoo\\Documents\\IntelliJ\\Collager\\utils\\purple50x50.ppm",
            0, 0);

    assertEquals(168, layer.getLayerImage()[50][50].getRed());
    assertEquals(91, layer.getLayerImage()[50][50].getGreen());
    assertEquals(188, layer.getLayerImage()[50][50].getBlue());

    assertNotEquals(168, layer.getLayerImage()[99][99].getRed());
    assertNotEquals(91, layer.getLayerImage()[99][99].getGreen());
    assertNotEquals(188, layer.getLayerImage()[99][99].getBlue());
  }

}
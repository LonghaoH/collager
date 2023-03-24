package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the Collager class.
 */
public class CollagerTest {
  @Test
  public void testNewProject() {
    //tests that height and width are correctly initialized
    ICollager collager = new CollagerPPM();
    assertEquals(0, collager.getHeight());
    assertEquals(0, collager.getWidth());
    assertEquals(255, collager.getMaxVal());
    collager.newProject(500, 500);
    assertEquals(500, collager.getHeight());
    assertEquals(500, collager.getWidth());
    assertEquals("background", collager.getLayers().get(0).getName());

    //tests invalid height and width
    try {
      collager.newProject(-5, 5);
      fail("Should not be able to initialize with negative value");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
    try {
      collager.newProject(5, -5);
      fail("Should not be able to initialize with negative value");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
  }

  @Test
  public void testAddLayer() {
    ICollager collager = new CollagerPPM();
    collager.newProject(500, 500);
    assertEquals(1, collager.getLayers().size());
    try {
      collager.addLayer("background");
      fail("Cannot add layer with same name");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
    collager.addLayer("Layer1");
    assertEquals("Layer1", collager.getLayers().get(1).getName());
    assertEquals(2, collager.getLayers().size());
  }

  @Test
  public void testGetHeight() {
    ICollager collager = new CollagerPPM();
    assertEquals(0, collager.getHeight());
    collager.newProject(500, 500);
    assertEquals(500, collager.getHeight());
  }

  @Test
  public void testGetWidth() {
    ICollager collager = new CollagerPPM();
    assertEquals(0, collager.getWidth());
    collager.newProject(500, 500);
    assertEquals(500, collager.getWidth());
  }

  @Test
  public void testGetMaxVal() {
    ICollager collager = new CollagerPPM();
    assertEquals(255, collager.getMaxVal());
  }

  @Test
  public void testGetLayers() {
    ICollager collager = new CollagerPPM();
    assertEquals(0, collager.getLayers().size());
    collager.newProject(500, 500);
    assertEquals(1, collager.getLayers().size());
    collager.addLayer("Hi");
    assertEquals(2, collager.getLayers().size());
    assertEquals("Hi", collager.getLayers().get(1).getName());
  }
}
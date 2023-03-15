package model;

/**
 * Represents a Collager program. Starts with a transparent background layer.
 */
public class Collager {
  private int height;
  private int width;
  private final int maxVal;
  private Layer background;

  /**
   * Initializes the Collager.
   */
  public Collager() {
    this.height = 0;
    this.width = 0;
    this.maxVal = 0;
    this.background = null;

  }

  public void newProject(int height, int width) {
    this.height = height;
    this.width = width;
    this.background = new Layer("background", height, width, new Filter()).whiteLayer();
  }


}

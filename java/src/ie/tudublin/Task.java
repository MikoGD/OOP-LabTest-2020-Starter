package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;
import java.util.ArrayList;

public class Task {
  ///////////////////////////////////
  // ATTRIBUTES
  protected final PApplet p;

  private String taskName;

  private int start, end;
  private int index;
  private int rectWidth = 1;
  private int length;

  private float border;
  private float oneDayWidth;

  private ArrayList<float[]> vertices = new ArrayList<float[]>();

  protected final int TOP_LEFT = 0;
  protected final int TOP_RIGHT = 1;
  protected final int BOTTOM_LEFT = 2;
  protected final int BOTTOM_RIGHT = 3;
  protected final int X = 0;
  protected final int Y = 1;

  ////////////////////////////////////
  // CONSTRUCTORS
  public Task(PApplet p, String taskName, int start, int end, int index, float border,
      int rowCount) {
    this.p = p;
    this.taskName = taskName;
    this.start = start;
    this.end = end;
    this.index = index;
    this.rectWidth = end - start;
    this.border = border;
    this.length = rowCount - 1;
    this.oneDayWidth = PApplet.map(2, 1, 30, border * 4, p.width - border)
        - PApplet.map(1, 1, 30, border * 4, p.width - border);
    getVertices();
  }

  public Task(PApplet p, TableRow tableRow, int index, float border, int rowCount) {
    this(p, tableRow.getString("Task"), tableRow.getInt("Start"), tableRow.getInt("End"), index,
        border, rowCount);
  }

  public Task() {
    this(null, "", 0, 0, 0, 0, 0);
  }

  //////////////////////////////////////
  // GETTERS

  public String getTaskName() {
    return taskName;
  }

  public int getStart() {
    return start;
  }

  public int getEnd() {
    return end;
  }

  public float[] getVertex(int vertex) {
    return vertices.get(vertex);
  }

  ////////////////////////////////////////////
  // SETTERS
  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public void setEnd(int end) {
    this.end = end;
  }

  public void setStartVertices() {
    float[] vertex1 = vertices.get(TOP_LEFT);
    float[] vertex2 = vertices.get(BOTTOM_LEFT);
    float[] vertex3 = vertices.get(BOTTOM_RIGHT);

    if (p.mouseX > vertex1[X] + oneDayWidth && p.mouseX < vertex3[X]) {
      vertex1[X] += oneDayWidth;
      vertex2[X] += oneDayWidth;
    } else if (p.mouseX < vertex1[X] - oneDayWidth && p.mouseX > border * 4 - oneDayWidth) {
      System.out.printf("moving left\n");
      vertex1[X] -= oneDayWidth;
      vertex2[X] -= oneDayWidth;
    }
  }

  public void setEndVertices() {
    float[] vertex1 = vertices.get(TOP_RIGHT);
    float[] vertex2 = vertices.get(BOTTOM_RIGHT);
    float[] vertex3 = vertices.get(BOTTOM_LEFT);

    if (p.mouseX > vertex1[X] + oneDayWidth && p.mouseX < p.width - oneDayWidth) {
      vertex1[X] += oneDayWidth;
      vertex2[X] += oneDayWidth;
    } else if (p.mouseX < vertex1[X] - oneDayWidth && p.mouseX > vertex3[X]) {
      vertex1[X] -= oneDayWidth;
      vertex2[X] -= oneDayWidth;
    }
  }

  ///////////////////////////
  // METHODS

  public void setup() {
    p.colorMode(PApplet.HSB);
  }

  public String toString() {
    String displayString = taskName + "\t" + start + "\t" + end + "\t" + index;

    return displayString;
  }

  public boolean isStartArea() {
    float[] vertex1 = vertices.get(TOP_LEFT);
    float[] vertex2 = vertices.get(BOTTOM_LEFT);

    if (p.mouseX >= vertex1[X] - 20 && p.mouseX <= vertex1[X] + 20 && p.mouseY >= vertex1[Y]
        && p.mouseY <= vertex2[Y]) {
      PApplet.println("start");
      return true;
    } else {
      return false;
    }
  }

  public boolean isEndArea() {
    float[] vertex1 = vertices.get(TOP_RIGHT);
    float[] vertex2 = vertices.get(BOTTOM_RIGHT);

    if (p.mouseX >= vertex1[X] - 20 && p.mouseX <= vertex1[X] + 20 && p.mouseY >= vertex1[Y]
        && p.mouseY <= vertex2[Y]) {
      PApplet.println("end");
      return true;
    } else {
      return false;
    }
  }

  public void getVertices() {
    float startX, endX, topY, bottomY;
    float[] vertex = new float[2];

    startX = PApplet.map(start, 1, 30, border * 4, p.width - border);
    endX = (oneDayWidth * rectWidth) + startX;
    topY = PApplet.map(index, 0, length, border * 2, p.height - (border * 2));
    bottomY = topY + 30;

    topY -= border / 4;
    bottomY -= border / 4;

    // Top left
    vertex[0] = startX;
    vertex[1] = topY;
    vertices.add(vertex.clone());

    // Top right
    vertex[0] = endX;
    vertex[1] = topY;
    vertices.add(vertex.clone());

    // Bottom Left
    vertex[0] = startX;
    vertex[1] = bottomY;
    vertices.add(vertex.clone());

    // Bottom Right
    vertex[0] = endX;
    vertex[1] = bottomY;
    vertices.add(vertex.clone());
  }

  public void drawText() {
    float y = PApplet.map(index, 0, length, border * 2, p.height - (border * 2));

    p.fill(255);
    p.textAlign(PApplet.CENTER, PApplet.CENTER);
    p.text(taskName, border * 3, y);

  }

  public void drawRectangle() {
    float colorGap;
    float[] vertex1 = vertices.get(TOP_LEFT);
    float[] vertex2 = vertices.get(BOTTOM_RIGHT);

    colorGap = 255 / length + 1;

    p.rectMode(PApplet.CORNERS);
    p.fill((colorGap * index) % 255, 255, 255);
    p.noStroke();
    p.rect(vertex1[X], vertex1[Y], vertex2[X], vertex2[Y], 5);
  }

  public void display() {
    drawText();
    drawRectangle();
  }
}

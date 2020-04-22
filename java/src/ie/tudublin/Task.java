package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;

public class Task {
  ///////////////////////////////////
  // ATTRIBUTES
  protected final PApplet p;

  private String taskName;
  private int start, end;
  private int index;

  ////////////////////////////////////
  // CONSTRUCTORS
  public Task(PApplet p, String taskName, int start, int end, int index) {
    this.p = p;
    this.taskName = taskName;
    this.start = start;
    this.end = end;
    this.index = index;
  }

  public Task(PApplet p, TableRow tableRow, int index) {
    this(p, tableRow.getString("Task"), tableRow.getInt("Start"), tableRow.getInt("End"), index);
  }

  public Task() {
    this(null, "", 0, 0, 0);
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

  ///////////////////////////
  // METHODS

  public void setup() {
    p.colorMode(PApplet.HSB);
  }

  public String toString() {
    String displayString = taskName + "\t" + start + "\t" + end + "\t" + index;

    return displayString;
  }

  public void display(float border, int size) {
    int length = size - 1;

    float colorGap = 255 / size;
    float y = PApplet.map(index, 0, length, border * 2, p.height - (border * 2));
    float rectX = PApplet.map(start, 1, 30, border * 4, p.width - border);
    float oneDayWidth = PApplet.map(2, 1, 30, border * 4, p.width - border)
        - PApplet.map(1, 1, 30, border * 4, p.width - border);

    p.fill(255);
    p.textAlign(PApplet.CENTER, PApplet.CENTER);
    p.text(taskName, border * 3, y);

    p.fill((colorGap * index) % 255, 255, 255);
    p.noStroke();
    p.rect(rectX, y, oneDayWidth * (end - start), 30);
  }
}

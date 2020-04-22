package ie.tudublin;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

import java.util.ArrayList;

public class Gantt extends PApplet {
  ///////////////////////////////////////////
  // VARIABLES
  ArrayList<Task> tasks = new ArrayList<Task>();
  float border;

  //////////////////////////////////////////
  // METHODS
  public void settings() {
    size(800, 600);
  }

  public void setup() {
    colorMode(HSB);
    border = width * 0.05f;
    loadTasks();
    printTasks();
  }

  public void loadTasks() {
    int i = 0;
    Table table = loadTable("tasks.csv", "header");

    for (TableRow currTableRow : table.rows()) {
      println(i);
      Task task = new Task(this, currTableRow, i);
      tasks.add(task);
      i++;
    }
  }

  public void printTasks() {
    for (Task currTask : tasks) {
      println(currTask);
    }
  }

  public void drawLines() {
    float x;

    for (int i = 0; i < 30; i++) {
      x = map(i, 0, 29, border * 4, width - border);

      stroke(255);
      line(x, border, x, height - border);

      textAlign(CENTER, CENTER);
      fill(255);
      text(i + 1, x, border / 2);
    }
  }

  public void drawTasks() {
    for (Task currTask : tasks) {
      currTask.display(border, tasks.size());
    }
  }

  ////////////////////////////////////////////////
  // EVENTS
  public void mousePressed() {
    println("Mouse pressed");
  }

  public void mouseDragged() {
    println("Mouse dragged");
  }

  ///////////////////////////////////////////
  // DRAW
  public void draw() {
    background(0);
    drawLines();
    drawTasks();
  }
}

package ie.tudublin;

import processing.core.PApplet;

public class Gantt extends PApplet {

  public void settings() {
    size(800, 600);
  }

  public void setup() {
    border = width * 0.05f;

    colorMode(HSB);
  }

  float border;

  public void loadTasks() {

  }

  public void printTasks() {

  }

  public void drawLines() {
    float x, y;

    for (int i = 0; i < 30; i++) {
      x = map(i, 0, 29, border * 4, width - border);

      stroke(255);
      line(x, border, x, height - border);

      textAlign(CENTER, CENTER);
      fill(255);
      text(i + 1, x, border / 2);
    }
  }

  public void mousePressed() {
    println("Mouse pressed");
  }

  public void mouseDragged() {
    println("Mouse dragged");
  }

  public void draw() {
    background(0);
    drawLines();
  }
}

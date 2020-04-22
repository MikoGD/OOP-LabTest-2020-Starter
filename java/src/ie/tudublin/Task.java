package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;

public class Task {
  public Task(PApplet p, String taskName, int start, int end) {
    this.p = p;
    this.taskName = taskName;
    this.start = start;
    this.end = end;
  }

  public Task(PApplet p, TableRow tableRow) {
    this(p, tableRow.getString("Task"), tableRow.getInt("Start"), tableRow.getInt("End"));
  }

  public Task() {
    this(null, "", 0, 0);
  }

  protected final PApplet p;

  String taskName;
  int start, end;
}

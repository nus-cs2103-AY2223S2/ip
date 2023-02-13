package Duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents task of the type deadline
 */
public class Deadline extends Task {
  public LocalDateTime time;
  public String strTime;
  public Deadline(String string, String time) {
    super(string);
    this.strTime = time;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    this.time = LocalDateTime.parse(time, formatter);
  }
  @Override
  public void printType() {
    System.out.print("[D]");
  }

  @Override
  public void printTime() {
    System.out.println(" (by: " + time + ")");
  }

  @Override
  public String toString() {
    return "deadline";
  }

  /**
   * Method return a String with information about a Deadline task:
   * type, marked or not, due time.
   */
  @Override
  public String taskString() {
    String mark;
    if(this.isMark) {
      mark = "[X]";
    } else {
      mark = "[ ]";
    }
    String type = "[D]";
    String time = "(by: " + this.time + ")";
    return type + mark + " " + this.string.trim()
                         + " " + time;
  }
}

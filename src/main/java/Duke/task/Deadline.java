package Duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents task of the type deadline
 */
public class Deadline extends Task {
    private LocalDateTime time;
    private String strTime;

    /**
   * Constructor for Deadline task.
   *
   * @param string string description of the task.
   * @param time   deadline time in String form.
   */
    public Deadline(String string, String time) {
        super(string);
        this.strTime = time;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.time = LocalDateTime.parse(time, formatter);
    }

    @Override
    public String toString() {
        return "deadline";
    }

    public String getStrTime() {
        return this.strTime;
    }

    public LocalDateTime getStartTime() {
        return this.time;
    }
    /**
      * Method return a String with information about a Deadline task:
      * type, marked or not, due time.
      */
    @Override
      public String taskString() {
        String mark;
        if (this.isMark()) {
            mark = "[X]";
        } else {
            mark = "[ ]";
        }
        String type = "[D]";
        String time = "(by: " + this.time + ")";
        return type + mark + " " + this.getString().trim()
                         + " " + time;
    }
}

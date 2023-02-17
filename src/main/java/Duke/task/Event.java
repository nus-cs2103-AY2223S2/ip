package Duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents task of the event deadline
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String strStartTime;
    private String strEndTime;

    /**
   * Constructor for Event task.
   *
   * @param string string description of the task.
   * @param startTime start time in String form.
   * @param endTime end time in String form.
   */
    public Event(String string, String startTime, String endTime) {
        super(string);
        this.strStartTime = startTime;
        this.strEndTime = endTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.startTime = LocalDateTime.parse(startTime, formatter);
        this.endTime = LocalDateTime.parse(endTime, formatter);
    }

    @Override
    public String toString() {
        return "event";
    }

    public String getStrStartTime() {
        return strStartTime;
    }

    public String getStrEndTime() {
        return strEndTime;
    }
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
   * Method return a String with information about an event task:
   * type, marked or not, start time and end time.
   */
    @Override
    public String taskString() {
        String mark;
        if (this.isMark()) {
            mark = "[X]";
        } else {
            mark = "[ ]";
        }
        String type = "[E]";
        String time = "(from: " + startTime
            + " to: " + endTime + ")";
        return type + mark + " " + this.getString().trim()
            + " " + time;
    }
}

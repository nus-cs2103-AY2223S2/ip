package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *      File name: Task.java
 *      @author: Jerome Neo
 *      Description: Task class that Todo, Event and Deadline inherits from.
 */
public class Task {
    private static final String SEPARATOR = "____________________________________________________________";
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string denoting the if the task has been completed with a "x",
     * else if it is not completed then " ".
     *
     * @return a string of "X" or " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the string description of the task.
     *
     * @return a string stored in task object.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the status of the Task object as being completed.
     */
    public void taskDone() {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
        System.out.println(SEPARATOR);
        isDone = true;
    }
    /**
     * Identical to taskDone() but returns a String instead.
     * @return String message
     */
    public String taskDoneString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append(this.toString() + "\n");
        isDone = true;
        return sb.toString();
    }
    /**
     * Sets the status of the Task object as being incomplete.
     */
    public void taskNotDone() {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
        System.out.println(SEPARATOR);
        isDone = false;
    }

    /**
     * Identical to taskNotDone() but returns a String instead.
     * @return String message
     */
    public String taskNotDoneString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"OK, I've marked this task as not done yet:\"\n");
        sb.append(this.toString() + "\n");
        isDone = false;
        return sb.toString();
    }


    /**
     * Returns a boolean of the completion status of the task.
     *
     * @return true if completed, else false.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns a LocalDateTime object that can be stored.
     * This method accepts Strings with the following format dd/MM/yyyy HHmm
     * where the time is in 24 hours.
     * @param stringDate
     * @return the LocalDateTime representation of the string.
     */
    public static LocalDateTime convertDateTime(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(stringDate, formatter);
    }

    /**
     * Returns a String object by formatting the LocalDateTime object into the
     * preferred String representation.
     *
     * @param dt
     * @return formatted date as e.g. "1 January 2023, 1200H".
     */
    public String dateTimeToString(LocalDateTime dt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, HHmm'H'");
        return dt.format(formatter);
    }
}


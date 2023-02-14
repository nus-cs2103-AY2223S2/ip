package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private final LocalDateTime by;

    /**
     * @param description the description of the task.
     * @param by the deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        String[] dateAndTime = by.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (dateAndTime.length == 1) {
            by += " 2359";
        }
        this.by = LocalDateTime.parse(by, formatter);
    }


    /**
     * @return the string representation of the task to be stored in the hard disk.
     */
    public String toStorageString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + formatter.format(this.by);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + formatter.format(this.by) + ")";
    }
}

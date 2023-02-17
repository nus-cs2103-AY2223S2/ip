package duke.task;

import java.time.LocalDateTime;

/**
 *      File name: Deadline.java
 *      @author: Jerome Neo
 *      Description: Deadline class that inherits from the Task class.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDateTime byDateTime;

    /**
     * Constructor for Deadline class.
     *
     * @param description of the task.
     * @param by the date in format dd/MM/yyyy HHmm.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byDateTime = Task.convertDateTime(by);
    }

    /**
     * Returns a string of the deadline date.
     *
     * @return by date.
     */
    public String getBy() {
        return by;
    }

    /**
     *  Returns a LocalDateTime object.
     * @return by date.
     */
    public LocalDateTime getByDateTime() {
        return byDateTime;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return a string.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] "
            + this.getDescription() + " (by: " + dateTimeToString(byDateTime) + ")";
    }
}

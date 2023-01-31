package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a deadline - a task that should be completed within
 * a particular date and time.
 */
public class Deadline extends Task {

    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the String representation of the deadline to be stored in the text file.
     *
     * @return The String representation of the deadline to be stored in the text file.
     */
    @Override
    public String getFileRepresentation() {
        String mark = (super.isDone) ? "X" : " ";

        return "D" + "~" + mark + "~" + this.description + "~" + this.by;
    }

    @Override
    public String toString() {

        return "[D]" + super.toString()
                + " (by: " + Task.getDateTimeString(this.by) + ")";
    }
}

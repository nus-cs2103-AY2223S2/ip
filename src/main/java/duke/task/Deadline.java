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

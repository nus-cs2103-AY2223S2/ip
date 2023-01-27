package duke.task;

import java.time.LocalDateTime;

/**
 * The duke.task.Deadline class is a type of task.
 *
 * @author Chia Jeremy
 */

public class Deadline extends Task {

    protected LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.dateTime.toLocalDate() + " " + this.dateTime.toLocalTime() + ")";
    }
}

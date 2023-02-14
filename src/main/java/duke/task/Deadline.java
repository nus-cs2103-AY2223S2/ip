package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    public LocalDateTime date;

    /**
     * Constructs a new Deadline instance.
     * 
     * @param description Description of the task.
     * @param date Date and time of the due date.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.type = 'D';
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDateTime() {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + type + "]" + "[" + getStatusIcon()+ "] " + this.description + " (by: " + 
                date.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma")) + ")";
    }
}

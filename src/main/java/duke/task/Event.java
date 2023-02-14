package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task
 */
public class Event extends Task {
    public LocalDateTime dateTimeFrom;
    public LocalDateTime dateTimeTo;

    /**
     * Constructs a new Todo instance.
     * 
     * @param description Description of the task.
     * @param dateTimeFrom Starting date and time.
     * @param dateTimeTo Ending date and time.
     */
    public Event(String description, LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo) {
        super(description);
        this.type = 'E';
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDateTime() {
        return dateTimeFrom.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma")) + " - " +
                dateTimeTo.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + type + "]" + "[" + getStatusIcon()+ "] " + this.description + " (from: " + 
                dateTimeFrom.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma")) + " to: " +
                dateTimeTo.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma")) + ")";
    }
}
package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates the Event class.
 */
public class Event extends Task {
    /**
     * Consistent serialVersionUID value
     */
    private static final long serialVersionUID = -4219171854668205715L;
    /** Start date and time of event */
    private final LocalDateTime startDateTime;
    /** End date and time of event */
    private final LocalDateTime endDateTime;

    /**
     * Constructs an Event.
     *
     * @param description Description of task.
     * @param startDateTime Start date and time of task.
     * @param endDateTime End date and time of task.
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Gets start date and time of task.
     *
     * @return Start date and time of task.
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Gets end date and time of task.
     *
     * @return End date and time of task.
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * {@inheritDoc}
     *
     * Includes type of task and its start and end dates.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String start = this.startDateTime.format(formatter);
        String end = this.endDateTime.format(formatter);
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}

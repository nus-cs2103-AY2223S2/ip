package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class inherits from the Task class and represents an event task.
 */
public class Event extends Task {
    /** Start date time of the event */
    protected LocalDateTime from;

    /** End date time of the event */
    protected LocalDateTime to;

    /**
     * Initializes the event task.
     *
     * @param description Description of the event task.
     * @param from Start date time of the event.
     * @param to End date time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        String from = this.from.format(format);
        String to = this.to.format(format);
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

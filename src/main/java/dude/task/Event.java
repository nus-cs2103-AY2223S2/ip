package dude.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task with start date and end date
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Initializes Event.
     *
     * @param description Description of task.
     * @param from Start date of task.
     * @param to End date of task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toRaw() {
        return "E | " + (this.isDone ? 1 : 0) + " | " + description + " | "
                + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | "
                + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + "\n";
    }
}

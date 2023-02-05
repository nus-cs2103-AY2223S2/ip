package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task.
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    /** Start date of this Event */
    protected LocalDate start;
    /** End date of this Event */
    protected LocalDate end;

    /**
     * Constructor to initialize an Event.
     *
     * @param start The start date of this Event.
     * @param end The end date of this Event.
     * @param desc The description of this Event.
     */
    public Event(LocalDate start, LocalDate end, String desc) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + start.format(FORMATTER) + " to: " + end.format(FORMATTER) + ")";
    }
}

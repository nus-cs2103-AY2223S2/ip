package duke.task;

import com.sun.jdi.LocalVariable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    /**
     * Constructor to initialize an event object
     *
     * @param start The start date/time of the event
     * @param end   The end date/time of the event
     * @param desc  The title of the event
     */
    public Event(LocalDate start, LocalDate end, String desc) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of the event
     *
     * @return The string representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(FORMATTER) + " to: " + end.format(FORMATTER) + ")";
    }
}

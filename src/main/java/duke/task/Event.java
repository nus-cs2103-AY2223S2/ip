package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Handles Event object.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for Event object.
     *
     * @param description Description of Event.
     * @param from From Date/Time of Event.
     * @param to To Date/Time of Event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for Event object.
     *
     * @param description Description of Event.
     * @param marked Boolean of Event status.
     * @param from From Date/Time of Event.
     * @param to To Date/Time of Event.
     */
    public Event(String description, boolean marked, LocalDate from, LocalDate to) {
        super(description, marked);
        this.from = from;
        this.to = to;
    }

    /**
     * Return string of Event.
     *
     * @return Formatted Event with details as String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

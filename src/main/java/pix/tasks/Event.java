package pix.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Event task which contains the description, from date and to date.
 */
public class Event extends Task {
    /** Date and time event starts. */
    protected LocalDateTime from;

    /** Date and time event ends. */
    protected LocalDateTime to;

    /**
     * Constructs a new Event.
     *
     * @param description Description of event.
     * @param from When event starts.
     * @param to When event ends.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, formatInput);
        this.to = LocalDateTime.parse(to, formatInput);
    }

    /**
     * Gets event starting date.
     *
     * @return Date which event starts.
     */
    public LocalDate getFromDate() {
        return this.from.toLocalDate();
    }


    /**
     * Gets event ending date.
     *
     * @return Date which event ends.
     */
    public LocalDate getToDate() {
        return this.to.toLocalDate();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(formatOutput) + " "
                + "\n              to: " + to.format(formatOutput) + ")";
    }

    @Override
    public String toSave() {
        return "E /" + super.toSave() + " / " + from.format(formatInput) + " / " + to.format(formatInput);
    }
}

package pix.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Event task which contains the description, eventStart date and eventEnd date.
 */
public class Event extends Task {
    /** Date and time event starts. */
    protected LocalDateTime eventStart;

    /** Date and time event ends. */
    protected LocalDateTime eventEnd;

    /**
     * Constructs a new Event.
     *
     * @param description Description of event.
     * @param eventStart When event starts.
     * @param eventEnd When event ends.
     */
    public Event(String description, String eventStart, String eventEnd) {
        super(description);
        this.eventStart = LocalDateTime.parse(eventStart, formatInput);
        this.eventEnd = LocalDateTime.parse(eventEnd, formatInput);
    }

    /**
     * Gets event starting date.
     *
     * @return Date which event starts.
     */
    public LocalDate getFromDate() {
        return this.eventStart.toLocalDate();
    }

    /**
     * Gets event ending date.
     *
     * @return Date which event ends.
     */
    public LocalDate getToDate() {
        return this.eventEnd.toLocalDate();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "\nStart: " + eventStart.format(formatOutput)
                + "\nEnd: " + eventEnd.format(formatOutput);
    }

    @Override
    public String toSave() {
        return "E /" + super.toSave() + " / " + eventStart.format(formatInput) + " / " + eventEnd.format(formatInput);
    }
}

package jeo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task
 */
public class Event extends Task {
    protected final String DATE_TIME_PARSED = "yyyy-MM-dd HH:mm";
    protected final String DATE_TIME_TO_PRINT = "d MMM yyyy HH:mm";
    protected String from;
    protected String to;
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    /**
     * Creates an Event task object
     * @param description Describes the task
     */
    public Event(String description, String from, String to) throws DateTimeParseException {
        super(description);
        DateTimeFormatter formatterParse = DateTimeFormatter.ofPattern(DATE_TIME_PARSED);
        DateTimeFormatter formatterPrint = DateTimeFormatter.ofPattern(DATE_TIME_TO_PRINT);
        this.fromDateTime = LocalDateTime.parse(from, formatterParse);
        this.from = this.fromDateTime.format(formatterPrint);
        this.toDateTime = LocalDateTime.parse(to, formatterParse);
        this.to = this.toDateTime.format(formatterPrint);
    }

    public LocalDateTime getDateTimeFrom() {
        return fromDateTime;
    }

    public LocalDateTime getDateTimeTo() {
        return toDateTime;
    }

    public String getFormattedFrom() {
        return from;
    }

    public String getFormattedTo() {
        return to;
    }

    /**
     * String representation of event task
     * @return String representation of event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFormattedFrom() + " to: " + getFormattedTo() + ")";
    }
}

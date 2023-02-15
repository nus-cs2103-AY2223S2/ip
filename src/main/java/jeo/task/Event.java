package jeo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that lasts for a period of time.
 * @author Goh Jun How
 * @version 0.3
 */
public class Event extends Task {
    protected static final String DATE_TIME_PARSED = "yyyy-MM-dd HH:mm";
    protected static final String DATE_TIME_TO_PRINT = "d MMM yyyy HH:mm";
    protected String fromDateString;
    protected String toDateString;
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    /**
     * Creates an Event task object with the specified description, start, and end date-time.
     * @param description String describing the task.
     * @param from String representing the start date-time of the task.
     * @param to String representing the end date-time of the task.
     */
    public Event(String description, String from, String to, String tags) throws DateTimeParseException {
        super(description, tags);
        DateTimeFormatter formatterParse = DateTimeFormatter.ofPattern(DATE_TIME_PARSED);
        DateTimeFormatter formatterPrint = DateTimeFormatter.ofPattern(DATE_TIME_TO_PRINT);
        this.fromDateTime = LocalDateTime.parse(from, formatterParse);
        this.fromDateString = this.fromDateTime.format(formatterPrint);
        this.toDateTime = LocalDateTime.parse(to, formatterParse);
        this.toDateString = this.toDateTime.format(formatterPrint);
    }

    /**
     * Gets the parsed start date-time.
     * @return the parsed start date-time.
     */
    public LocalDateTime getDateTimeFrom() {
        return fromDateTime;
    }

    /**
     * Gets the parsed end date-time.
     * @return the parsed end date-time.
     */
    public LocalDateTime getDateTimeTo() {
        return toDateTime;
    }

    /**
     * Gets the formatted start date-time.
     * @return the formatted start date-time.
     */
    public String getFormattedFrom() {
        return fromDateString;
    }

    /**
     * Gets the formatted end date-time.
     * @return the formatted end date-time.
     */
    public String getFormattedTo() {
        return toDateString;
    }

    /**
     * Gets the string representation of the event task.
     * @return String representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFormattedFrom() + " to: " + getFormattedTo() + ")";
    }
}

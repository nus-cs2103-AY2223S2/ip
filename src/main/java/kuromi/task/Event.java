package kuromi.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import kuromi.exception.KuromiException;

/**
 * Event task represented by description, start date, and end date. Extends from Task class.
 */
public class Event extends Task {
    /** The start date represented as a String **/
    protected String by;
    /** The end date represented as a String **/
    protected String from;

    protected LocalDateTime dateFrom;

    protected LocalDateTime dateBy;

    /**
     * Main constructor (for invocation by most classes)
     * @param description The description of an event.
     * @param by The end date of an event.
     * @param from The start date of an event.
     */
    public Event(String description, String by, String from) throws KuromiException {
        super(description);
        this.from = from;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.dateFrom = LocalDateTime.parse(from, format);
        } catch (DateTimeParseException e) {
            throw new KuromiException("OOPS!!! The format of the end date must be in yyyy-MM-dd HH:mm");
        }
        if (this.dateFrom.isBefore(LocalDateTime.now())) {
            throw new KuromiException("OOPS!!! The start date should be greater than the current date");
        }
        this.by = by;
        try {
            this.dateBy = LocalDateTime.parse(by, format);
        } catch (DateTimeParseException e) {
            throw new KuromiException("OOPS!!! The format of the start date must be in yyyy-MM-dd HH:mm");
        }
        if (this.dateBy.isBefore(this.dateFrom)) {
            throw new KuromiException("OOPS!!! The end date must be after the start date");
        }
    }

    /**
     * Secondary constructor (for invocation by Storage to put task in data into TaskList)
     *
     * @param description The description of an event.
     * @param by The end date of an event.
     * @param from The start date of an event.
     * @param isDone The status of an event.
     */
    public Event(String description, String by, String from, boolean isDone) throws KuromiException {
        super(description, isDone);
        this.by = by;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.dateBy = LocalDateTime.parse(by, format);
        this.from = from;
        this.dateFrom = LocalDateTime.parse(from, format);
    }

    /**
     * Gets the detailed description of an event.
     * To store the current data into the file.
     *
     * @return Detailed description as a String.
     */
    @Override
    public String getDetailedDescription() {
        return super.description + " | " + this.from + " | " + this.by;
    }

    /**
     * Get the symbol of an event.
     *
     * @return The symbol of an event.
     */
    @Override
    public String getSymbol() {
        return "E";
    }

    @Override
    public LocalDateTime getEndDate() {
        return this.dateBy;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.by + ")";
    }

}

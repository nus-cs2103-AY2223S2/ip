package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Extends the Task class, representing a task that is a type of event.
 *  @author Muhammad Reyaaz
 *  @version %I% %G%
 *  @see Task
 *  @since 11
 */

class Events extends Task {

    protected String from;
    protected String to;

    /**
     * Makes the default constructor explicit
     */
    protected Events() {

    }

    /**
     * Constructs an Events object with the specified description, start date and end date.
     * @param description The description of the event
     * @param from The start date of the event
     * @param to The end date of the event
     */
    Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Events object with the specified description, start date, end date and completion status.
     * @param description The description of the event
     * @param from The start date of the event
     * @param to The end date of the event
     * @param isDone The completion status of the event
     */
    Events(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start date of the event.
     * @return The start date of the event
     */
    String getFrom() {
        return this.from;
    }

    /**
     * Gets the end date of the event.
     * @return The start date of the event
     */
    String getTo() {
        return this.to;
    }

    /**
     * Converts the given date string into the specified format (MMM dd yyyy).
     * @param date The date string to be converted
     * @return The converted date string in the specified format, or the original date string if there is a parsing exception
     */
    String localDateParser(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            return date;
        }
    }

    /**
     * Marks a new Events object with the completion status marked as done.
     *  @return A new Events object with the completion status marked as done
     */
    @Override
    Events markAsDone() {
        return new Events(getDescription(), from, to, true);
    }

    /**
     * Marks a new Events object with the completion status marked as undone.
     *  @return A new Events object with the completion status marked as undone
     */
    @Override
    Events markAsUndone() {
        return new Events(getDescription(), from, to, false);
    }

    /**
     * Represents the event object as a string.
     * @return string representation of the event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + localDateParser(from) + ")" + "(to: " + localDateParser(to) + ")";
    }
}

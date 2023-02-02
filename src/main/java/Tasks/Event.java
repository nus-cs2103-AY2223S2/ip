package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.InvalidDateFormatException;

/**
 * This class is used to represent an Event task.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime until;

    /**
     * Constructor for the Event.
     * @param description The description for the Event.
     * @param from The starting date of the Event.
     * @param until The ending date of the Event.
     */
    public Event(String description, String from, String until) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String[] temp = from.split(" ");
        if (temp.length == 1) {
            from += " 0000";
        }
        try {
            this.from = LocalDateTime.parse(from, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(e);
        }

        temp = until.split(" ");
        if (temp.length == 1) {
            until += " 0000";
        }
        try {
            this.until = LocalDateTime.parse(until, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(e);
        }
    }

    /**
     * Returns the starting date of the Event in LocalDateTime.
     * @return The starting date of the Event in LocalDateTime.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Returns the ending date of the Event in LocalDateTime.
     * @return The ending date of the Event in LocalDateTime.
     */
    public LocalDateTime getUntil() {
        return this.until;
    }

    /**
     * Returns the string representation of the Event.
     * @return The string representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.getMonth().toString().substring(0, 3)
            + " " + this.from.getDayOfMonth() + " " + this.from.getYear() + ", " + this.from.toLocalTime() + " | to: "
            + this.until.getMonth().toString().substring(0, 3) + " " + this.until.getDayOfMonth() + " "
            + this.until.getYear() + ", " + this.until.toLocalTime() + ")";
    }
}

package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.InvalidDateFormatException;

/**
 * This class is used to represent the Deadline task.
 */
public class Deadline extends Task {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected LocalDateTime by;

    /**
     * Constructor for the Deadline.
     * @param description The description for the Deadline.
     * @param by The deadline to finish the task.
     */
    public Deadline(String description, String by) {
        super(description);
        String[] temp = by.split(" ");
        if (temp.length == 1) {
            by += " 0000";
        }
        try {
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(e);
        }
    }

    /**
     * Returns the deadline of the task in LocalDateTime.
     * @return The deadline of the task in LocalDateTime.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Returns the string representation of the Deadline.
     * @return The string representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.getMonth().toString().substring(0, 3)
                + " " + this.by.getDayOfMonth() + " " + this.by.getYear() + ", " + this.by.toLocalTime() + ")";
    }
}

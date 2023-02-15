package jeo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that has a deadline.
 * @author Goh Jun How
 * @version 0.3
 */
public class Deadline extends Task {
    protected static final String DATE_TIME_TO_PARSE = "yyyy-MM-dd HH:mm";
    protected static final String DATE_TIME_TO_PRINT = "d MMM yyyy HH:mm";
    protected String dueDate;
    protected LocalDateTime byDateTime;

    /**
     * Creates a Deadline task object with the specified description and due date-time.
     * @param description String describing the task.
     * @param by String representing the date-time when the task is due.
     */
    public Deadline(String description, String by, String tags) throws DateTimeParseException {
        super(description, tags);
        DateTimeFormatter formatterParse = DateTimeFormatter.ofPattern(DATE_TIME_TO_PARSE);
        DateTimeFormatter formatterPrint = DateTimeFormatter.ofPattern(DATE_TIME_TO_PRINT);
        this.byDateTime = LocalDateTime.parse(by, formatterParse);
        this.dueDate = byDateTime.format(formatterPrint);
    }

    /**
     * Gets the parsed due date-time.
     * @return the parsed due date-time.
     */
    public LocalDateTime getDateTimeBy() {
        return byDateTime;
    }

    /**
     * Gets the formatted due date-time.
     * @return the formatted due date-time.
     */
    public String getFormattedBy() {
        return dueDate;
    }

    /**
     * Gets the string representation of the deadline task.
     * @return String representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedBy() + ")";
    }
}

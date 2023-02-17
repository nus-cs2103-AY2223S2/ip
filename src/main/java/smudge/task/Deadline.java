package smudge.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Deadline class represents a task of deadline type
 */
public class Deadline extends Task {

    private final LocalDateTime by;

    /**
     * constructor method to create a deadline type task
     * @param description name of deadline
     * @param by date and time deadline is due
     * @throws DateTimeParseException when the date time is invalid
     */
    public Deadline(String description, LocalDateTime by) throws DateTimeParseException {
        super(description);
        this.by = by;
    }

    /**
     * getter method for the date time of the deadline
     * @return due datetime of the deadline
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * method to return deadline task in string
     * @return deadline task in string
     */
    @Override
    public String toString() {
        String dateFormat = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[D]" + super.toString() + " (by: " + dateFormat + ")";
    }
}
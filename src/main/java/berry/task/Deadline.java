package berry.task;

import berry.exception.IncorrectDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    /** The date to do the task by */
    private LocalDate by;

    public Deadline(String description, String by) throws IncorrectDateException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new IncorrectDateException();
        }
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description);
        this.isDone = isDone;
        this.by = LocalDate.parse(by);
    }

    /**
     * {@inheritDoc}
     *
     * @return a string representing a deadline task to be saved into the file
     */
    @Override
    public String interpretTaskToText() {
        return "D | " + this.getStatusIcon() + " | " + this.description + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
package iris.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import iris.exception.DateTimeException;

/**
 * Represents a task with a deadline
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructor for a Deadline task
     * @param name the description of the task
     * @param by the deadline of the task in the format: {dd-MM-yyyy HHmm}. Eg. 20-02-2002 2000
     * @throws DateTimeException when the date for the deadline cannot be understood
     */
    public Deadline(String name, String by) throws DateTimeException {
        super(name);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        try {
            this.deadline = LocalDateTime.parse(by, format);
        } catch (DateTimeParseException e) {
            throw new DateTimeException();
        }
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String storageFormat() {
        return String.join("|", "D", super.storageFormat(), dateString(deadline)) + "\n";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + dateString(deadline) + ")";
    }
}

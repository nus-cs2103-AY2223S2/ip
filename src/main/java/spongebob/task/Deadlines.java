package spongebob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import spongebob.exception.SpongebobInvalidArgumentException;

/**
 * Deadline type of task
 */
public class Deadlines extends Task {
    private final String DEADLINE;
    /**
     * End time of deadline in "dd/MM/yyyy HH:mm" format
     */
    private final LocalDateTime FORMATTED_DEADLINE;
    private final String STANDARD_FORMAT = "dd/MM/yyyy HH:mm";
    private final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(STANDARD_FORMAT);

    /**
     * Constructor to create a deadline task.
     *
     * @param description description of the task.
     * @param deadline deadline of the task.
     * @throws SpongebobInvalidArgumentException indicate that a command has been passed an illegal argument.
     */
    public Deadlines(String description, String deadline) throws SpongebobInvalidArgumentException {
        super(description);
        try {
            this.DEADLINE = deadline;
            this.FORMATTED_DEADLINE = LocalDateTime.parse(deadline, FORMAT);
        } catch (DateTimeParseException e) {
            throw new SpongebobInvalidArgumentException(
                    String.format("The format of date-time is invalid. Should be %s.", STANDARD_FORMAT));
        }
    }

    public String getDeadline() {
        return DEADLINE;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), FORMATTED_DEADLINE.format(FORMAT));
    }
}

package duke.task;

import duke.exception.DukeInvalidArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline type of task
 */
public class Deadlines extends Task {
    private String deadline;
    /**
     * End time of deadline in "dd/MM/yyyy HH:mm" format
     */
    private LocalDateTime formattedDeadline;
    private final String STANDARD_FORMAT = "dd/MM/yyyy HH:mm";
    private final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(STANDARD_FORMAT);

    /**
     * Constructor to create a deadline task.
     *
     * @param description description of the task.
     * @param deadline deadline of the task.
     * @throws DukeInvalidArgumentException indicate that a command has been passed an illegal argument.
     */
    public Deadlines(String description, String deadline) throws DukeInvalidArgumentException {
        super(description);
        try {
            this.deadline = deadline;
            this.formattedDeadline = LocalDateTime.parse(deadline, FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException(
                    String.format("The format of date-time is invalid. Should be %s.", STANDARD_FORMAT));
        }
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), deadline);
    }
}

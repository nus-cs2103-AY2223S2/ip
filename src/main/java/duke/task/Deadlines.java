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
     * End time of deadline in "YYYY-MM-DDTHH:MM:SS" format
     */
    private LocalDateTime formattedDeadline;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

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
            this.formattedDeadline = LocalDateTime.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException("The format of date-time is invalid.");
        }
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), formattedDeadline.format(format));
    }
}

package duke.task;

import duke.exception.DukeInvalidArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline type of task
 */
public class Deadlines extends Task {
    protected String ddl;
    /**
     * End time of deadline in "YYYY-MM-DDTHH:MM:SS" format
     */
    protected LocalDateTime by;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    /**
     * Constructor to create a deadline task.
     *
     * @param description description of the task.
     * @param by deadline of the task.
     * @throws DukeInvalidArgumentException indicate that a command has been passed an illegal argument.
     */
    public Deadlines(String description, String by) throws DukeInvalidArgumentException {
        super(description);
        try {
            this.ddl = by;
            this.by = LocalDateTime.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException("The format of date-time is invalid.");
        }
    }

    public String getBy() {
        return ddl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(format) + ")";
    }
}

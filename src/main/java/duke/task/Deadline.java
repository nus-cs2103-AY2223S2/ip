package duke.task;

import duke.TaskCreationException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 **/
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Creates a deadline
     *
     * @param desc     description of task
     * @param deadline deadline of task
     * @throws TaskCreationException when creation fails due to date parsing
     */
    public Deadline(String desc, String deadline) throws TaskCreationException {
        super(desc);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new TaskCreationException("Error parsing date");
        }
    }

    @Override
    protected String getType() {
        return "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", deadline);
    }
}

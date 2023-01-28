package duke.task;

import duke.TaskCreationException;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 **/
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Creates a deadline
     * @param desc description of task
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
        return String.format("%s%s %s (by: %s)", getType(), getStatusIcon(), this.desc, deadline);
    }
}

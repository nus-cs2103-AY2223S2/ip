package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * The Deadline class extends the Task class to represent a task with a due date.
 *
 * @author owen-yap
 */
public class Deadline extends Task {

    protected final LocalDateTime by;
    /**
     * Constructs a new Deadline object with a description and a due date.
     *
     * @param description the description of the task
     * @param by the due date of the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline object with a description, a due date, and a status.
     *
     * @param description the description of the task
     * @param by the due date of the task
     * @param isDone the status of the task
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }
    /**
     * Overrides the `toString` method to return the Deadline task in the form:
     * "[D][taskStatus] taskDescription (by: dueDate)".
     *
     * @return a string representation of the Event task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"))
                + ")";
    }
}

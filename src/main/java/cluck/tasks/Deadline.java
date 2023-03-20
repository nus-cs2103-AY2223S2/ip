package cluck.tasks;

import java.time.LocalDateTime;

/**
 * Deadline is a Task that has an additional field to indicate the due date.
 */
public class Deadline extends Task {
    protected LocalDateTime dueDate;

    /**
     * Instantiates a new Deadline.
     *
     * @param description Deadline description
     * @param dueDate     The due date.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = interpretLocalDateTime(dueDate);
    }

    /**
     * Instantiates a new Deadline.
     *
     * @param isMarked    Whether task is marked.
     * @param description Deadline description.
     * @param dueDate     The task's deadline.
     */
    public Deadline(boolean isMarked, String description, String dueDate) {
        super(isMarked, description);
        this.dueDate = interpretLocalDateTime(dueDate);
    }

    @Override
    public String makeSaveFormat() {
        return "D" + super.makeSaveFormat() + "|" + this.dueDate.format(DEFAULT_FORMATTER) + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.dueDate.format(DEFAULT_FORMATTER) + ")";
    }
}

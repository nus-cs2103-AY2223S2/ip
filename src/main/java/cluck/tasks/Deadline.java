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
     * @param description deadline description
     * @param dueDate     the due date
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = interpretLocalDateTime(dueDate);
    }

    /**
     * Instantiates a new Deadline.
     *
     * @param isMarked    whether task is marked
     * @param description deadline description
     * @param dueDate     when is the tasks deadline
     */
    public Deadline(boolean isMarked, String description, String dueDate) {
        super(isMarked, description);
        this.dueDate = interpretLocalDateTime(dueDate);
    }

    @Override
    public String makeSaveFormat() {
        return "E" + super.makeSaveFormat() + "|" + this.dueDate + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.dueDate.format(FORMATTER) + ")";
    }
}

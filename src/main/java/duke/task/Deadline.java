package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a Task with a due date.
 */
public class Deadline extends Task {

    private LocalDateTime dueBy;

    /**
     * Constructs an instance of Deadline with specified description and due date.
     *
     * @param description Task description.
     * @param dueBy Due date of task.
     */
    public Deadline(String description, LocalDateTime dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    /**
     * Constructs an instance of Deadline with specified task status, description and due date.
     *
     * @param isDone Status of the task (completed or not completed).
     * @param description Task description.
     * @param dueBy Due date of task.
     */
    public Deadline(boolean isDone, String description, LocalDateTime dueBy) {
        super(isDone, description);
        this.dueBy = dueBy;
    }

    /**
     * Returns the due date of the task.
     *
     * @return Due date of task.
     */
    LocalDateTime getDueBy() {
        return dueBy;
    }

    /**
     * Updates the due date of the task with specified dua date.
     *
     * @param dueBy New due date of the task.
     */
    void setDueBy(LocalDateTime dueBy) {
        this.dueBy = dueBy;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toCsv() {
        return "D," + super.toCsv() + "," + dueBy;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (By: "
                + dueBy.getDayOfMonth() + " " + dueBy.getMonth() + " " + dueBy.getYear() + " "
                + dueBy.getHour() + dueBy.getMinute() // TODO: bugalert (may not always be 4-digits)
                + ")";
    }
}

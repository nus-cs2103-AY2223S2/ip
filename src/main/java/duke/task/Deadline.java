package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime doneBy;

    /**
     * Constructor for Deadline.
     * @param description Task description.
     * @param doneBy Deadline of task.
     */
    public Deadline(String description, LocalDateTime doneBy) {
        super(description);
        this.doneBy = doneBy;
    }

    /**
     * Constructor for Deadline.
     * @param description Task description.
     * @param isDone Task's completed status.
     * @param doneBy Deadline of task.
     */
    public Deadline(String description, boolean isDone, LocalDateTime doneBy) {
        super(description, isDone);
        this.doneBy = doneBy;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getSaveTaskString() {
        return String.format("D | %s | by: %s", super.toString(), formatSavedDateTime(doneBy));
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return String.format("D | %s | by: %s", super.toString(), formatDateTime(doneBy));
    }
}

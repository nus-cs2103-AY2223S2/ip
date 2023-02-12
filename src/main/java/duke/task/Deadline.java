package duke.task;

import duke.tag.Tag;

import java.time.LocalDateTime;

/**
 * A Deadline which is a type of Task.
 */
public class Deadline extends Task {
    private LocalDateTime doneBy;

    /**
     * Constructor for Deadline.
     *
     * @param description Task description.
     * @param doneBy Deadline of task.
     */
    public Deadline(String description, LocalDateTime doneBy) {
        super(description);
        this.doneBy = doneBy;
    }

    public Deadline(String description, LocalDateTime doneBy, Tag tag) {
        super(description, tag);
        this.doneBy = doneBy;
    }

    /**
     * Constructor for Deadline.
     *
     * @param description Task description.
     * @param isDone Task's completed status.
     * @param doneBy Deadline of task.
     */
    public Deadline(String description, boolean isDone, LocalDateTime doneBy) {
        super(description, isDone);
        this.doneBy = doneBy;
    }

    public Deadline(String description, boolean isDone, LocalDateTime doneBy, Tag tag) {
        super(description, isDone, tag);
        this.doneBy = doneBy;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getSaveTaskString() {
        return String.format("D | %s | by: %s%s", super.toString(), formatSavedDateTime(doneBy),
                getTagString());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return String.format("D | %s | by: %s%s", super.toString(), displayDateTime(doneBy), getTagString());
    }
}

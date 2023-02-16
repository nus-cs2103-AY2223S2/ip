package duke.task;

import java.time.LocalDateTime;

import duke.tag.Tag;

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

    /**
     * Constructor for Deadline.
     *
     * @param description Task description.
     * @param doneBy Deadline of task.
     * @param tag Tag object that task is tagged to.
     */
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
     * @param tag Tag object that task is tagged to.
     */
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

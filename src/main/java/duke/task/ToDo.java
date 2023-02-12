package duke.task;

import duke.tag.Tag;

/**
 * A ToDo which is a type of Task.
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo.
     *
     * @param description Task description.
     */
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, Tag tag) {
        super(description, tag);
    }

    /**
     * Constructor for ToDo.
     *
     * @param description Task description.
     * @param isDone Task's completed status.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public ToDo(String description, boolean isDone, Tag tag) {
        super(description, isDone, tag);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getSaveTaskString() {
        return toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return String.format("T | %s%s", super.toString(), getTagString());
    }
}

package duke.task;

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

    /**
     * Constructor for ToDo.
     *
     * @param description Task description.
     * @param isDone Task's completed status.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
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
        return String.format("T | %s", super.toString());
    }
}

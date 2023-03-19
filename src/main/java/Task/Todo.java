package duke.task;

/**
 * Represents a Task without deadline or duration.
 */
public class Todo extends Task {

    /**
     * Initialises new instance of Todo.
     *
     * @param description The name of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initialises new instance of Todo.
     *
     * @param description The name of the task.
     * @param isDone A boolean representing whether task has been completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("T | %s", super.toString());
    }

}

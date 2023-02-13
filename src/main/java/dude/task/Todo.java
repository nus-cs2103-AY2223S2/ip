package dude.task;

/**
 * Task with description
 */
public class Todo extends Task {
    /**
     * Initializes Todo.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toRaw() {
        return "T | " + (this.isDone ? 1 : 0) + " | " + description + "\n";
    }
}

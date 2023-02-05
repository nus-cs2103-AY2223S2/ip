package duke.tasks;

/**
 * Represents a task to be done
 */
public class Todo extends Task {
    /**
     * Initialises to-do class
     *
     * @param name name of the task
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

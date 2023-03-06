package duke.task;

/**
 * Creates the Todo class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo.
     *
     * @param description Description of todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     *
     * Includes type of task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

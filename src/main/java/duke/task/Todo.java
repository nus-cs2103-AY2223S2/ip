package duke.task;

public class Todo extends Task {

    /**
     * Constructs Todo class.
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

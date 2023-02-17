package duke.task;

/**
 * A task in a To-do list.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo tasks
     *
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
        super.type = 'T';
    }

    @Override
    public String toString() {
        return "[" + super.type + "]" + super.toString();
    }
}

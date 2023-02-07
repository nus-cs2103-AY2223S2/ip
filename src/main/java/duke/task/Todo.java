package duke.task;

/**
 * The type Todo.
 */
public class Todo extends Task {
    /**
     * Instantiates a new Todo.
     *
     * @param description the description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + this.getStatusIcon() + " " + this.description;
    }

    @Override
    public String toSaveableString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }
}

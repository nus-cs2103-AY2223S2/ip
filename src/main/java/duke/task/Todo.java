package duke.task;

/**
 * Represents a Todo, which is a type of Task that has no datetime attached to it.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for Todo class, which sets its description and status.
     *
     * @param input of Todo task.
     * @param taskStatus of Todo task.
     */
    public Todo(String input, String taskStatus) {
        super(input);
        markTaskIfNeeded(taskStatus, this);
    }

    /**
     * Returns the String representation of a Todo.
     *
     * @return String representation of a Todo in this format: [T] [{status}] {description}.
     */
    @Override
    public String toString() {
        return String.format("[T][%c] %s", getStatusIcon(), description);
    }
}

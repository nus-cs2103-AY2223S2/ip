package duke.task;

/**
 * Represents a Todo, which is a type of Task that has no datetime attached to it.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, String taskStatus) {
        super(description);

        if (taskStatus.equals("1")) {
            this.mark();
        }
    }

    /**
     * Returns the String representation of a Todo.
     *
     * @return String representation of a Todo in this format: [T] [{status}] {description}.
     */
    @Override
    public String toString() {
        return String.format("[T][%c] %s", this.getStatusIcon(), this.description);
    }
}

package duke.task;

/**
 * Represents a Todo task which has no given deadline.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task with a description.
     *
     * @param description Describes the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of the Todo task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toCsvString() {
        return String.format("T,%s", super.toCsvString());
    }
}

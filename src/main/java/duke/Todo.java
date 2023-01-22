package duke;

/**
 * Represents a Todo task. A Todo task has a description and can be marked as done.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean bool) {
        super(description, bool);
    }

    /**
     * Marks a Todo task as done.
     * @return Todo task marked as done.
     */
    @Override
    public Todo markAsDone() {
        return new Todo(description, true);
    }

    /**
     * Unmarks a Todo task from being done.
     * @return Todo task unmarked from being done.
     */
    @Override
    public Todo unmarkAsDone() {
        return new Todo(description);
    }

    /**
     * Returns data for storage purposes.
     * @return Data for storage purposes.
     */
    @Override
    public String getDataToSave() {
        return "T / " + getStatusNum() + " / " + getDesc();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

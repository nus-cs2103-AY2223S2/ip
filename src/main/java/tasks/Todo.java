package tasks;

/**
 * Represents a simple to-do task
 */
public class Todo extends Task {
    /**
     * Constructs a Todo with the given description.
     *
     * @param description the description of the to-do task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo with the given description and status.
     *
     * @param description the description of the to-do task
     * @param isDone the status of the to-do task (completed or not)
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String parseToSave() {
        return "T" + " | " + super.parseToSave();
    }
}

package berry.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * {@inheritDoc}
     *
     * @return a string representing a todo task to be saved into the file
     */
    @Override
    public String interpretTaskToString() {
        return "T | " + this.getStatusIcon() + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
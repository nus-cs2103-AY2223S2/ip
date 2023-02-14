package duke.task;

/**
 * Represents a Todo that is a Task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Translates the task into data format.
     * @return A String that represents the task in data format.
     */
    public String toData() {
        return String.format("T | %s | %s", this.getStatusIcon(), this.getDescription());
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.getDescription());
    }
}

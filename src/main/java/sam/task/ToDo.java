package sam.task;

/**
 * Represents a simple task.
 */
public class ToDo extends Task {
    public ToDo(String title) {
        this(title, false);
    }

    /**
     * Constructs a new ToDo task.
     *
     * @param title The title of the task.
     * @param isDone Indicates whether the task is done.
     */
    public ToDo(String title, boolean isDone) {
        super(title, isDone);
    }

    @Override
    public String toSaveFormat() {
        return String.format(
                "T | %d | %s",
                getStatusNo(), getTitle());
    }

    @Override
    public String toString() {
        return String.format(
                "[T][%c] %s",
                getStatusIcon(), getTitle());
    }
}

package duke.task;

/**
 * Represents a ToDo object.
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo.
     * @param title Title of ToDo task.
     */
    public ToDo(String title) {
        super(title);
    }

    /**
     * Constructor for ToDo.
     * @param title Title of ToDo task.
     * @param isDone Indicate if the task should be marked.
     */
    public ToDo(String title, boolean isDone) { super(title, isDone); }

    /**
     * Returns the string format to be saved on disk.
     * @return String to be saved on disk.
     */
    @Override
    public String toDiskFormat() {
        return String.format("T|%d|%s", super.getIsDone() ? 1 : 0, super.getTitle());
    }

    @Override
    public String toString() {
        return String.format("[%s][T] %s", super.getIsDone() ? "X" : " ", super.getTitle());
    }
}

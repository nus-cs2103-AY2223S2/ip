package duke.taskers;

/**
 * Task class.
 */
public abstract class Task {

    private final String description;
    private boolean isDone;

    /**
     * Constructor for that Task class.
     *
     * @param description The description of the Task.
     * @param isDone Boolean to show if the task is done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Helps to format the string for file input.
     *
     * @return A partially formatted string.
     */
    public String helpFormatString() {
        return ((this.isDone ? "1" : "0") + " / "
                + this.description.trim()).trim();
    }

    public abstract String formatStringForFile();

    /**
     * Formats for the task string.
     *
     * @return The string representing the task.
     */
    @Override
    public String toString() {
        return (this.isDone ? "[X]" : "[ ]")
                + this.description;
    }

}

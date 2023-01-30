package duke.taskers;

public class Task {
    private final String description;
    private boolean isDone;

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

    /**
     * Formats for the task string.
     *
     * @return The string representing the task.
     */
    @Override
    public String toString() {
        return (this.isDone ? "[X]" : "[ ]") +
                this.description;
    }

}

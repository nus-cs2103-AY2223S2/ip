package duke.taskers;

public abstract class Task {

    private final String description;
    private boolean isDone;

    /**
     * Task constructor.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is done.
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
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the tast.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Helps to format the string for file input.
     *
     * @return A partially formatted string.
     */
    public String helpFormatString() {
        return ((this.isDone ? "1" : "0") + " / " + this.description.trim()).trim();
    }


    public abstract String formatStringForFile();

    /**
     * Format for the task string.
     *
     * @return The string representing the task.
     */
    @Override
    public String toString() {
        return (this.isDone ? "[X]" : "[ ]") + this.description;
    }

}

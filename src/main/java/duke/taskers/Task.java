package duke.taskers;

/**
 * Task class.
 */
public abstract class Task {

    private final String description;
    private boolean isDone;
    private boolean isPriority;

    /**
     * Constructor for that Task class.
     *
     * @param description The description of the Task.
     * @param isDone Boolean to show if the task is done or not.
     */
    public Task(String description, boolean isDone, boolean isPriority) {
        this.description = description;
        this.isDone = isDone;
        this.isPriority = isPriority;

    }

    /**
     * Makes the item prioritised.
     */
    public void prioritise() {
        this.isPriority = true;
    }

    public void unprioritise() {
        this.isPriority = false;
    }

    /**
     * Gets the description from the task.
     *
     * @return The description string from the task.
     */
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
        String doneStr = this.isDone ? "1" : "0";
        String isPrior = this.isPriority ? "1" : "0";
        return doneStr + " / " + isPrior + " / " + this.description.trim();
    }

    public abstract String formatStringForFile();

    /**
     * Formats for the task string.
     *
     * @return The string representing the task.
     */
    @Override
    public String toString() {
        String doneStr = this.isDone ? "[X]" : "[ ]";
        String isPrior = this.isPriority ? "[!!!]" : "[ ]";
        return doneStr + isPrior + this.description;
    }

}

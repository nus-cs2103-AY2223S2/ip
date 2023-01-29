package duke.task;

public class Task {
    protected String description = "";
    protected boolean isDone;

    /**
     * Constructor for a task.
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark a task status as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark a task status as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Gets the status of the given task.
     * @return Status of the task.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done duke.task with X
    }

    /**
     * Returns the string representation of the task.
     * @return Representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string representation of the task in the save file.
     * @return Representation of the task in the save file.
     */
    public String toSavedString() {
        return "";
    }
}

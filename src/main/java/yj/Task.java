package yj;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task - either [X] or [].
     *
     * @return status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    public String toString() {
        return description;
    }

    /**
     * Returns the string representation of the task to be saved in the file.
     *
     * @return string representation of the task to be saved in the file.
     */
    public String toSaveString() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}


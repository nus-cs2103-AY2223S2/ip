package duke.task;

/**
 * Represents a task. It can be a 'to do', 'deadline' or 'event' task.
 */
public abstract class Task {
    protected boolean isDone;
    protected String description;
    protected String duedateString;
    protected char symbol;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getDuedateString() {
        return duedateString;
    }
    public char getSymbol() {
        return symbol;
    }

    /**
     * Gets the status icon of a task to indicate if it is marked or not.
     * @return An "X" or " " if it is marked or unmarked respectively.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Converts the task to a specified string format of
     * [Symbol],[Marked/UnMarked],[Description],[Deadline if any] to save.
     * @return String with the specified save format of a task.
     */
    public abstract String saveTask();
}

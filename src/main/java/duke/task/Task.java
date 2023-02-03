package duke.task;

public abstract class Task {
    public String description;
    protected boolean isDone;
    public char symbol;

    public String duedateString;


    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
     * Converts the task to a specified string format of [Symbol],[Marked/UnMarked],[Description],[Deadline if any] to save.
     * @return String with the specified save format of a task.
     */
    public abstract String saveTask();
}

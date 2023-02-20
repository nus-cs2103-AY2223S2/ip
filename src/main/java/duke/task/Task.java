package duke.task;

/**
 * Represents a task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    public void setNotDone() {
        isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public String getTaskString() {
        return (isDone ? 1 : 0) + " | " + description;
    }

    public String getDescription() {
        return description;
    }

    public abstract String getTaskState();
}

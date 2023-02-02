package duke.task;

public abstract class Task {
    private final String description;
    private boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    public String getTaskString() {
        return (this.isDone ? 1 : 0) + " | " + this.description;
    }

    public String getDescription() {
        return description;
    }

    public abstract String getTaskState();
}

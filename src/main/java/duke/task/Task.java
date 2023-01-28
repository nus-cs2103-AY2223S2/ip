package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean marked) {
        this.description = description;
        this.isDone = marked;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setStatus(boolean status) {
        this.isDone = status;
    }

    @Override
    public String toString() {
        return String.format("[" + getStatusIcon() + "] " + this.description);
    }
}
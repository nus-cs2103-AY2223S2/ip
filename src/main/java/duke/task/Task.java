package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() { return (isDone ? "X" : " "); }

    public String getDescription() {
        return description;
    }

    public abstract String getTaskSymbol();

    public String toString() { return "[" + getStatusIcon() + "] " + description; }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() { isDone = false; }
}
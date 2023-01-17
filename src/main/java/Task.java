public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String status() {
        return "[" + getStatusIcon() + "] ";
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return this.status() + this.getDescription();
    }
}

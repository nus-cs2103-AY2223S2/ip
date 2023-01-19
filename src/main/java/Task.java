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

    public Boolean markAsDone() {
        this.isDone = true;
        return isDone;
    }

    public Boolean markAsUndone() {
        this.isDone = false;
        return isDone;
    }

    public String toString() {
        return this.description.toString();
    }
}
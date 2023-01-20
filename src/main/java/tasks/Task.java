package tasks;

public class Task {
    boolean isDone = false;
    String description;

    public Task(String description) {
        this.description = description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    protected String getStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s\n", getStatus(), this.description);
    }
}

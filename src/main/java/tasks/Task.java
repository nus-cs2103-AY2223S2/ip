package tasks;

public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return isDone ? "[X] " + description + '\n' : "[ ] " + description + '\n';
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }
}

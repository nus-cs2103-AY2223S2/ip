public class Task {
    private String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String toString() {
        return (isDone ? "[X] " : "[] ") + this.description;
    }
    public boolean isDone() {
        return this.isDone;
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsUndone() {
        this.isDone = false;
    }
}

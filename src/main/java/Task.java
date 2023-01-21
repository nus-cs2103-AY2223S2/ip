public abstract class Task {
    private String description;

    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // returns String representation of task
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    // 2 commands to change isDone status of task
    public void mark() { this.isDone = true; }

    public void unmark() {this.isDone = false; }
}

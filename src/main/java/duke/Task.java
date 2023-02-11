package duke;

/**
 * Represents a generic task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + this.description;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return isDone;
    }
}

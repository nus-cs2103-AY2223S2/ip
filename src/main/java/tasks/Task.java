package tasks;

import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 4048021127880559361L;

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

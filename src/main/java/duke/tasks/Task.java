package duke.tasks;

import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 4048021127880559361L;

    protected boolean isDone = false;
    private String description;

    public Task(String description) {
        this.description = description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean contains(String keyword) {
        return description.contains(keyword);
    }

    protected String getStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s\n", getStatus(), this.description);
    }
}

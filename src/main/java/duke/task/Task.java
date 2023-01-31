package duke.task;

import java.io.Serializable;

public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean isDone() { return this.isDone; }

    /**
     * The contains method checks if the target string is contained in the description field.
     *
     * @param target The string to search for in the description field.
     * @return true if the target string is found in the description, false otherwise.
     */
    public boolean contains(String target) {
        return this.description.contains(target);
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}

//...

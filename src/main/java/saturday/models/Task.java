package saturday.models;

import java.io.Serializable;

public class Task implements Serializable {
    private boolean done;
    private String description;

    public Task(String description) {
        this.done = false;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.done;
    }

    public void mark() {
        this.done = true;
    }

    public void unMark() {
        this.done = false;
    }

    @Override
    public String toString() {
        String checkbox = done ? "[X] " : "[ ] ";
        return checkbox + description;
    }
}
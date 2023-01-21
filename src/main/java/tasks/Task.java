package tasks;

import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected DateTimeFormatter formatInput = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected DateTimeFormatter formatOutput = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return isDone ? "[X] " + description : "[ ] " + description;
    }

    public String toSave() { return isDone ? " 1 / " + description : " 0 / " + description; }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }
}

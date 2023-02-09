package duke;

import duke.Ui;
// custom duke.Task class to store individual tasks that the user enters
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void toggleMarked() {
        this.isDone = !this.isDone;
    }

    public String getDescription() {
        return description;
    }

    public String toSavedString() {
        return this.isDone ? "1 | " + this.description : "0 | " + this.description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}


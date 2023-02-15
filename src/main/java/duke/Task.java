package duke;

import duke.Ui;
// custom duke.Task class to store individual tasks that the user enters
public abstract class Task {
    protected String description;
    protected boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public Task(Boolean isMarked, String Task_content) {
        this.description = Task_content;
        this.isMarked = isMarked;
    }

    public String getStatusIcon() {
        return (isMarked ? "[X]" : "[ ]"); // mark done task with X
    }

    public void toggleMarked() {
        this.isMarked = !this.isMarked;
    }

    public String getDescription() {
        return description;
    }

    public String toSavedString() {
        return this.isMarked ? "1 | " + this.description : "0 | " + this.description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    public boolean isMarked() {
        return this.isMarked;
    }
    public abstract String addDivider();
}


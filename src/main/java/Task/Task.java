package Task;

import Ui.Ui;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return ( isDone ? "[X]" : "[ ]");
    }

    public void mark_done() {
        this.isDone = true;
    }

    public void mark_undone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    public String getCommand() {
        return this.description;
    }
}
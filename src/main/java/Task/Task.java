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

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }

        return ((Task) o).description == this.description && ((Task) o).isDone == this.isDone;
    }

    public String getCommand() {
        return this.description;
    }
}
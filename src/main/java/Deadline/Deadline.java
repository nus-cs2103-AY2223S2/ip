package Deadline;

import Task.Task;

public class Deadline extends Task {
    
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone, String description, String by) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }

    @Override
    public String getCommand() {
        return this.isDone
        ? "1 deadline " + this.description + " /by " + this.by
        : "0 deadline " + this.description + " /by " + this.by;
    }
}

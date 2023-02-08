package seedu.duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean isDone() {
        return this.isDone;
    }

    // Mark task as done
    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:" + "\n" + this);
    }

    // Unmark task
    public void unmark() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:" + "\n" + this);
    }

    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }
}



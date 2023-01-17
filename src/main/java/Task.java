/**
 * The Task class represent the tasks added by the user.
 *
 * @author Chia Jeremy
 */

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkedDone() {
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (this.isDone ? "[X] " : "[ ] ");   // mark done task with X
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}

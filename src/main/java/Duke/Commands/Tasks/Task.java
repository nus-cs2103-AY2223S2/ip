package Duke.Commands.Tasks;

// skeleton code for this class is taken from cs2103 website
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        this.description = description.trim();
        this.isDone = isDone;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}

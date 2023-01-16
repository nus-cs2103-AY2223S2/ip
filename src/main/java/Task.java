public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done task with X
    }

    public void markAsDone() {
        this.isDone = true; // Mark done
    }

    public void unmarkAsDone() {
        this.isDone = false; // Unmark done
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}

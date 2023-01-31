public class Task {
    protected String description;
    protected boolean isDone;
    protected String fullDescription;

    public Task(String description, String fullDescription) {
        this.description = description;
        this.isDone = false;
        this.fullDescription = fullDescription;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public boolean isDoneStatus() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
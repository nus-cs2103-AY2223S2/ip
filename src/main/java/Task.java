public class Task {
    public final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String statusIcon = isDone ? "X" : " ";
        return String.format("[%s] %s", statusIcon, description);
    }
}

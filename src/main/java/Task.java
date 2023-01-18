public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task markAsDone() {
        this.isDone = !this.isDone;
        return this;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] "
                + description;
    }
}

package duke;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getFileRepresentation() {
        return this.isDone + "|" + this.description;
    };

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String mark() {
        this.isDone = true;
        return "[X] " + description;
    }

    public String unmark() {
        this.isDone = false;
        return "[ ] " + description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

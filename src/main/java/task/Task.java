package task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String remarks;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.remarks = "";
    }

    public Task(String description, String remarks) {
        this(description);
        this.remarks = remarks;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getRemarks() {
        return remarks;
    }
    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + description);
    }
}
package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done duke.task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void markUnmark(boolean marked) {
        this.isDone = marked;
    }
}
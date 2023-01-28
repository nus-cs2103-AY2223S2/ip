package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDetailedDescription() {
        return this.description;
    }

    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getSymbol() {
        return "";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {

        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}

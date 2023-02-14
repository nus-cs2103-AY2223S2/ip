package duke;


public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String statusToString() {

        return "[" + getStatusIcon() + "] ";
    }

    public void setDone() {

        this.isDone = true;
    }

    public void setUndone() {

        this.isDone = false;
    }

    public String getDescription() {

        return this.description;
    }

    public String toString() {
        return this.statusToString() + this.getDescription();
    }
    public String getSaveString() {
        if (isDone) {
            return "done";
        } else {
            return "undone";
        }
    }
}

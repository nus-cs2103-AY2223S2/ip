package crystal.task;

public abstract class Task {
    protected String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


    public String toPrint() {
        return this.toString();
    }



}

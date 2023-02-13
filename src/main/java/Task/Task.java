package Task;

/**
 * Parent class task.
 * Subclass:ToDo,Deadline,Event
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type = "Task";

    /**
     * Constructor
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[] "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescriptionAndTime() {
        return description;
    }

    public String getType() {
        return type;
    }

    public boolean equals(Task t) {
        if (t.getType().equals(this.getType()) && t.getDescriptionAndTime().equals(this.getDescriptionAndTime())) {
            return true;
        }
        return false;
    }
}

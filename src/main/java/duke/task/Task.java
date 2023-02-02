package duke.task;

/**
 * Contains information of a task
 * Contains description and completion status of the task
 */
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

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String save() {
        return "  | " +  getStatusIcon()
                + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] "
                + description;
    }
}

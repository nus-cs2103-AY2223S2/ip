package duke;

/**
 * Class of task
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    /**
     * Constructor for the Task Class
     * @param description name of the class
     * @param c type of the task
     * @param isDone whether the task is done
     */
    public Task(String description, char c, Boolean isDone) {
        this.description = description;
        this.type = c;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getTaskDesc() {
        return this.description;
    }

    public String savedAs() {
        return (this.type + "|" + this.isDone + "|" + this.description);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

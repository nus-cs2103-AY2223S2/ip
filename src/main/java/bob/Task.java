package bob;

/** Abstract class that can be separated as Todos, Deadlines and Events */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Returns an unmarked Task object with given description, type
     * @param description Given task description
     * @param type Given task type
     */
    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    /** Return string representation of task status */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /** Return type of task */
    public String getTaskType() {
        return this.type;
    }

    /** Mark task as done */
    public void mark() {
        this.isDone = true;
    }

    /** Unmark task as not done */
    public void unmark() {
        this.isDone = false;
    }


}

package duke.task;

/*
 * Represents a task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected char type = ' ';

    /**
     * Returns date and time.
     *
     * @return String representation of date and time but returns null if its a Todo task.
     */
    public abstract String getDateTime();

    /**
     * Constructs a new task instance.
     * 
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves mark status of task.
     * 
     * @return Return a string "X" if the task is mark as completed, otherwise return blank
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the status of the current task.
     * 
     * @return false if task is unmark and True if task is mark.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the type of the current task.
     * 
     * @return Type of current task.
     */
    public char getType() {
        return this.type;
    }

    /**
     * Returns the description of the current task.
     * 
     * @return Description of current task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }


}

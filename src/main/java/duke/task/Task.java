package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected char type = ' ';

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieve mark status of task
     * 
     * @return Return a string "X" if the task is mark as completed, otherwise return blank
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Return the status of the current task
     * 
     * @return False if task is unmark and True if task is mark
     */

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Return the type of the current task
     * 
     * @return Type of current task
     */
    public char getType() {
        return this.type;
    }

    /**
     * Return the description of the current task
     * 
     * @return Description of current task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Mark the task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmark the task as not done
     */
    public void unmark() {
        this.isDone = false;
    }
}

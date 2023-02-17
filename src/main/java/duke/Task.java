package duke;

/**
 * Represents the abstract Task class which serves as a base template to handle all possible Tasks
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor method for Task
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * get the status of the task
     * @return an X if the task is completed
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the status of the task to completed
     */
    public void marked() {
        this.isDone = true;
    }

    /**
     * Sets the status of the task to incomplete
     */
    public void unmarked() {
        this.isDone = false;
    }

    /**
     * Prints out the description of the task
     * @return formatted description of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Abstract method to be implemented by child classes for formatting output when saving data to file
     * @return formatted string
     */
    public abstract String formatForFile();

    public abstract Type getType();
}

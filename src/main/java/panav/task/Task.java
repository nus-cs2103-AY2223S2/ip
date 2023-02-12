package panav.task;

/**
 * Abstract class to represent a Task in the TaskList.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor to initialise attributes.
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to show if a task is completed or not.
     * @return "X" is task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method to mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }


    /**
     * Method to mark a task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Method to check if a task is done or not.
     * @return true if done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Method to get command word of task.
     * @return command word.
     */
    public abstract String getCommand();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

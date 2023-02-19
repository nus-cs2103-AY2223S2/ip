package duke;


/**
 * Class representing a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Parameterized constructor to create a Task
     * @param description description of the task to be created
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Parameterized constructor to create a Task
     * @param description description of the task to be created
     * @param isDone if the task has been completed
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon indicating whether the Task has been completed or not.
     * Returns "X" if the task has been completed, " " otherwise.
     * @return the String representing the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the String representation of a Task which can be displayed to the user
     * @return the String representation of a Task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks a Task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a Task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the String representation of a Task which can be written to a file
     * @return the String to be written to a file
     */
    public String toFile() {
        return "/" + (this.isDone ? 1 : 0) + " /" + this.description;
    }
}

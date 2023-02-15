package duke;

/**
 * Models a Task.
 */
public abstract class Task {

    /** Boolean used to determine if the task is completed or not. */
    protected boolean taskStatus = false;
    /** String used to assign the name of the task. */
    protected String taskDesc;

    /**
     * Constructor for the Task class.
     *
     * @param taskDesc The name of the Task.
     */
    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    /**
     * Constructor for the Task class. Overloaded to allow setting of
     * the completion directly.
     *
     * @param taskDesc The name of the Task.
     * @param taskStatus The boolean value to be assigned for completion of Task.
     */
    public Task(String taskDesc, boolean taskStatus) {
        this.taskDesc = taskDesc;
        this.taskStatus = taskStatus;
    }

    /**
     * Method to mark task as done.
     */
    public void markDone() {
        taskStatus = true;
    }

    /**
     * Method to unmark task as done.
     */
    public void markUndone() {
        taskStatus = false;
    }

    /**
     * To String method to return the String representation of the task.
     *
     * @return Task in String representation.
     */
    public String toString() {
        if (taskStatus) {
            return "[X] " + taskDesc;
        } else {
            return "[ ] " + taskDesc;
        }
    }

    /**
     * Method to return the CSV representation of the task.
     *
     * @return Task in CSV representation.
     */
    public abstract String asCsv();
}

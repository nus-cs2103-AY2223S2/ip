package duke;

/**
 * Models a Task.
 */
public abstract class Task {

    /** Boolean used to determine if the task is completed or not. */
    protected boolean isDone = false;
    /** String used to assign the name of the task. */
    protected String name;

    /**
     * Constructor for the Task class.
     *
     * @param name The name of the Task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Constructor for the Task class. Overloaded to allow setting of
     * the completion directly.
     *
     * @param name The name of the Task.
     * @param b The boolean value to be assigned
     */
    public Task(String name, boolean b) {
        this.name = name;
        this.isDone = b;
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
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }

    /**
     * Method to return the CSV representation of the task.
     *
     * @return Task in CSV representation.
     */
    public abstract String asCsv();
}

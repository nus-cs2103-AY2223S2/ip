package lele.task;

/**
 * The parent for all the types of task available
 * for the user to create.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task instance, usually stemming
     * from its child instances.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gives the status of the task.
     *
     * @return Returns the string of the task status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done Duke.task with X
    }

    /**
     * For its child classes to instantiate and override,
     * not to be run by Task.
     *
     * @return A string to indicate that this shouldn't be run by Task.
     */
    public String getName() {
        assert false : "Method should not be called on the parent class"; // Should not reach here
        return "Should not be returned";
    }

    /**
     * Provides the description of the task.
     * Mainly for passing to other methods.
     *
     * @return Description of the task in string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the status of the task.
     *
     * @param status The status to be marked.
     */
    public void markStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Marks the status of the task.
     * This is mainly for the purpose of loading
     * the data of the user.
     *
     * @param s String indicating its status.
     */
    public void markStatus(String s) {
        this.isDone = !s.equals("0");
    }

    /**
     * To be overridden and appended by its child classes.
     *
     * @return A string representation of task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}

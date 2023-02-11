package duke.task;

/**
 * Represents a Task.
 * @author pzhengze
 */
public abstract class Task {
    /** Storage of the description of Task */
    protected final String description;

    /** Boolean showing if the task has been done*/
    protected boolean isDone;

    /**
     * Constructor for the Task object.
     * @param description The description of the Task.
     * @param isDone The boolean showing if the task has been done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the Task as done.
     * @return Message to user informing about successful execution.
     */
    public String mark() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n  %s", this);
    }

    /**
     * Marks the Task as not done.
     * @return Message to user informing about successful execution.
     */
    public String unMark() {
        this.isDone = false;
        return String.format("Ok, I've marked this task as not done yet:\n  %s", this);
    }

    /**
     * Checks if description contains s.
     * @param s The String to check for.
     * @return True if description contains s. Else False.
     */
    public boolean contain(String s) {
        int index = description.toLowerCase().indexOf(s);

        return index != -1;
    }

    /**
     * Returns the Task in String form.
     * @return The Task in String form.
     */
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Returns the Task in String form specific for saving.
     * @return The Task in String form
     */
    public abstract String save();

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}

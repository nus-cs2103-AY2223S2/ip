/**
 * Project name: Duke
 * @author Tan Jun Da A023489eU
 */

package seedu.duke.task;

/**
 * Represents a task. A <code>Task</code> object corresponds to a
 * description by a string.
 * e.g., <code>"read book"</code>
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the class Task.
     *
     * @param description The description of the object Task.
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Method to get the status of the Task.
     *
     * @return A String indicating X for done and space for not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Setter to set the Task to marked.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Setter to set the Task to unmarked.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Getter for the variable description of this object instance.
     *
     * @return The string of the variable description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Override toString method to get the icon and description of this object.
     *
     * @return The String icon and description of this object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

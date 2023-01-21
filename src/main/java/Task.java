/**
 * Project name: Duke
 * Author: Tan Jun Da
 * Student Number: A0234893U
 *
 * This class is for the Task added by the User.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the class Task.
     * @param description The description of the object Task.
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Method to get the status of the Task.
     * @return A string indicating X for done and space for not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Setter method to set the Task to marked.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Setter method to set the Task to unmarked.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Override toString method to get the icon and description of this object.
     * @return The String icon and description of this object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

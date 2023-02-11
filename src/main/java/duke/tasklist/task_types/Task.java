package duke.tasklist.task_types;

import java.io.Serializable;

/**
 * Represents a <code>task</code> object that contains a string holding the
 * <code>name</code> of the
 * task and the <code>isMarked</code> of the task. Represents a <code>task</code>
 * object that contains
 * a string holding the <code>name</code> of the task and the
 * <code>isMarked</code> of the task.
 * 
 * @author Brian Quek
 */

public class Task implements Serializable {
    protected String name;
    protected boolean isMarked;

    /**
     * Constructor for the Task object.
     */
    public Task(String name) {
        this.name = name;
        this.isMarked = false;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Sets the status field based on the input argument.
     * 
     * @param status a boolean value that is used to change the status
     */
    public void setMarked(boolean status) {
        this.isMarked = status;
    }

    /**
     * @return a String containing the status of the task and the task name.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isMarked ? "X" : " ", this.name);
    }

}

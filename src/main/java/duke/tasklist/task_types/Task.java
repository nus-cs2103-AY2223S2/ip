package duke.tasklist.task_types;

import java.io.Serializable;

/**
 * Represents a <code>task</code> object that contains a string holding the <code>name</code> of the
 * task and the <code>status</code> of the task.
 * 
 * 
 * @author Brian Quek
 */

public abstract class Task implements Serializable {
    protected String name;
    protected boolean status;

    /**
     * Constructor for the Task object.
     */
    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    /**
     * Sets the status field based on the input argument.
     * 
     * @param status a boolean value that is used to change the status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return a String containing the status of the task and the task name.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.status ? "X" : " ", this.name);
    }

    public abstract String toTextString();
}

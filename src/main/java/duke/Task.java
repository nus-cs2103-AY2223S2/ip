package duke;

import java.io.Serializable;

/**
 * The parent class of the specific tasks.
 */
public class Task implements Serializable {
    private String name;
    private boolean isDone;

    /**
     * Constructs the class.
     *
     * @param name
     */
    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    /**
     * Modifies the status of task.
     *
     * @param status
     */
    public void setStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Returns the status of the task.
     *
     * @return the status of the task.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns the name of the task.
     *
     * @return the name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the toString.
     *
     * @return the task list
     */
    public String toString() {
        return name;
    }
}

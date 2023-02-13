package duke;

import java.io.Serializable;

/**
 * The parent class of the specific tasks.
 */
public class Task implements Serializable {
    private String name;
    private boolean isDone;

    /**
     * The constructor of this class.
     *
     * @param name
     */
    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    /**
     * The method that modifies the status of task.
     *
     * @param status
     */
    public void setStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * The method that returns the status of the task.
     *
     * @return the status of the task.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * The method that returns the name of the task.
     *
     * @return the name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * The toString method.
     *
     * @return the task name.
     */
    public String toString() {
        return name;
    }
}

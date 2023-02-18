package duke.tasktype;

import java.io.Serializable;

/**
 * The class used to store task information.
 */
public class Task implements Serializable {
    protected String cont;
    protected boolean finished;

    /**
     * The constructor of Task objects.
     *
     * @param cont the content of the task
     */
    public Task(String cont) {
        this.cont = cont;
        this.finished = false;
    }

    /**
     * Return whether the task is marked finished.
     *
     * @return a char indicating whether this task is finished or not
     */
    public String checkStatus() {
        return this.finished ? "X" : " ";
    }

    /**
     * Mark the task as finished.
     */
    public void mark() {
        this.finished = true;
    }

    /**
     * Mark the task as unfinished.
     */
    public void unmark() {
        this.finished = false;
    }

    /**
     * Override the toString() method to show the information of the task.
     *
     * @return a String that shows all information of the task
     */
    @Override
    public String toString() {
        return "[" + this.checkStatus() + "]" + this.cont;
    }
}

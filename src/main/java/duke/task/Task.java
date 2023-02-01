package duke.task;

import java.io.Serializable;

public class Task implements Serializable {
    protected String status;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.status = " ";
        this.description = description;
        this.isDone = false;
    }

    private String toggleStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns void.
     * <p>
     * Marks the task with a "X" and changes the status of isDone to True
     */
    public void mark() {
        if (this.isDone == false) {
            this.isDone = true;
            this.status = toggleStatus();
        }
    }

    /**
     * Returns void.
     * <p>
     * Marks the task with a " " and changes the status of isDone to False
     */
    public void unmark() {
        if (this.isDone == true) {
            this.isDone = false;
            this.status = toggleStatus();
        }
    }

    public String getStatus() {
        return "[" + this.status + "] " + this.description;
    }

    public String getDescription() {
        return this.description;
    }
}
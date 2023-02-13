package duke.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Parent Task class which implements Serializable.
 */
public class Task implements Serializable {
    protected String desc;
    protected boolean isDone;
    protected boolean hasDate;

    /**
     * Public constructor that takes in String to create a Task object.
     * Boolen isDone is initialised as false.
     * @param desc description of the task.
     */
    public Task(String desc) {
        this.desc = desc;
        setDone(false);
    }

    /**
     * Getter method to access Task description only.
     * @return desc description of the task.
     */
    public String getDescription() {
        return this.desc;
    }

    /**
     * Indicate whether task is done or not.
     * True if done, false otherwise.
     * @param done Boolean indicator if task is done or not.
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Check if task is done or not.
     * True if done, false otherwise.
     * @return boolean
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Indicate done if task is not done and vice-versa.
     */
    public void toggleDoneOrNot() {
        if (this.isDone()) {
            setDone(false);
        } else {
            setDone(true);
        }
    }

    public boolean hasDate() {
        return hasDate;
    }

    public LocalDateTime getDate() {
        return null;
    }

    /**
     * Human friendly interpretation of Task.
     * @return Task interpretation showing isDone status and description.
     */
    @Override
    public String toString() {
        String icon = isDone ? "[X]" : "[ ]";
        return icon + " " + this.getDescription();
    }
}

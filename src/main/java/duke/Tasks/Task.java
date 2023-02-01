package duke.Tasks;

import java.io.Serializable;

/**
 * Parent Task class which implements Serializable.
 */
public class Task implements Serializable {
    protected String desc;
    protected boolean isDone;

    /**
     * Public constructor that takes in String to create a Task object.
     * Boolean isDone is initialised as fale.
     * @param desc String description of the task to be created.
     */
    public Task(String desc) {
        this.desc = desc;
        setDone(false);
    }

    /**
     * Getter method to acces Task description only.
     * 
     * @return String description of the task.
     */
    public String getDescription() {
        return this.desc;
    }

    /**
     * Indicate whether task is done or not.
     * True if done, false otherwise.
     * 
     * @param done
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /** 
     * Getter method to check if task is done or not.
     * True if done, false otherwise.
     * 
     * @return
     */
    public boolean isDone() {   
        return this.isDone;   
    }

    /**
     * Toggles done status of task.
     */
    public void toggleDoneOrNot() {
        if (this.isDone()) {
            setDone(false);
        } else {
            setDone(true);
        }
    }

    /**
     * User friendly interpretaion of Task
     * 
     * @return Task interpretation showing isDone status and description.
     */
    @Override
    public String toString() {
        String icon = isDone ? "[X]" : "[ ]";
        return icon + " " + this.getDescription();
    }
}

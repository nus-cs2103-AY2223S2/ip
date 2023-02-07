package duke.tasks;

import java.io.Serializable;

/**
 * The abstract class Task is the superclass of Deadline, Event, and ToDo.
 * Subclasses of Task have to provide methods to get the type icon in order to display to the user.
 */

public abstract class Task implements Serializable{
    protected String description;
    protected boolean isDone;

    /**
     * Abstract constructor of a Task object.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Returns the string representing whether this Task isDone.
     * @return String representation of whether this task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Returns the string representing what this type of Task is.
     * @return String representation of task type.
     */
    abstract String getTypeIcon();

    /***
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String toString(){
        return getStatusIcon() + " " + this.description;
    }

    /***
     * Marks this Task as done.
     */
    public void markDone(){
        this.isDone = true;
    }

    /***
     * Undoes the mark status of this Task
     */
    public void unmark(){
        this.isDone = false;
    }

    public boolean containsString(String keyword) {
        if (this.description.contains(keyword)) {
            return true;
        } else {
            return false;
        }
    }
}
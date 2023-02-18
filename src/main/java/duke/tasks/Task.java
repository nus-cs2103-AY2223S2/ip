package duke.tasks;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * The abstract class Task is the superclass of Deadline, Event, and ToDo.
 * Subclasses of Task have to provide methods to get the type icon in order to display to the user.
 */

public abstract class Task implements Serializable {
    protected static final DateTimeFormatter DTFORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected String description;
    protected boolean isDone;

    /**
     * Abstract constructor of a Task object.
     * @param description of task
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
        // mark done task with X
        return (isDone ? "[X]" : "[ ]");
    }
    /***
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    /***
     * Marks this Task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /***
     * Undoes the mark status of this Task
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns true if description of task matches given keyword
     * @param keyword that is being searched
     * @return whether this task matches the keyword
     */
    public boolean containsString(String keyword) {
        return this.description.contains(keyword);
    }
}

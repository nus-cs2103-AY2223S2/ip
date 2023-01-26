package saturday.models;

import java.io.Serializable;
/**
 * The Task class is a model class that represents a task. It implements the Serializable interface
 * in order to allow for the serialization and deserialization of task objects.
 *
 * @author Titus Lowe
 * @version 0.1
 */
public class Task implements Serializable {
    private boolean done;
    private String description;

    /**
     * Constructs a new task with the specified description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.done = false;
        this.description = description;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is done or not.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Mark the task as done.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * unMark the task
     */
    public void unMark() {
        this.done = false;
    }

    /**
     * Returns a string representation of the task. The format of the returned string is "[X] " if the task is done
     * or "[ ] " if the task is not done, followed by the task description.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        String checkbox = done ? "[X] " : "[ ] ";
        return checkbox + description;
    }
}
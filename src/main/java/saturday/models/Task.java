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
    private int index;
    private boolean isDone;
    private boolean isDisplayed;
    private String description;

    /**
     * Constructs a new task with the specified description.
     *
     * @param description the description of the task
     */
    public Task(int index, String description) {
        this.index = index;
        this.isDone = false;
        this.isDisplayed = true;
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
     * Returns index of the task.
     *
     * @return index number
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Returns whether the task is isDone or not.
     *
     * @return true if the task is isDone, false otherwise
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Mark the task as isDone.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmark the task
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Display the task.
     */
    public void display() {
        this.isDisplayed = true;
    }

    /**
     * Un display the task.
     */
    public void unDisplay() {
        this.isDisplayed = false;
    }

    /**
     * Returns whether the task is being displayed or not.
     */
    public boolean isDisplayed() {
        return this.isDisplayed;
    }

    public boolean contains(String query) {
        return this.description.contains(query);
    }

    /**
     * Returns a string representation of the task. The format of the returned string is "[X] " if the task is isDone
     * or "[ ] " if the task is not isDone, followed by the task description.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        String checkbox = isDone ? "[X] " : "[ ] ";
        return checkbox + description;
    }
}
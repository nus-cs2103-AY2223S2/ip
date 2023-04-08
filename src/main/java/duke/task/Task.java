package duke.task;

import java.util.Arrays;

import duke.DukeException;

/**
 * Represents a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a task.
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the status of this task according to the argument.
     *
     * @param isDone Status of the task to be set.
     */
    public void markStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the status of the given task.
     * @return Status of the task.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done duke.task with X
    }

    /**
     * Returns the string representation of the task.
     * @return Representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string representation of the task in the save file.
     * @return Representation of the task in the save file.
     */
    public abstract String toSavedString();

    /**
     * Checks if this task's description matches any keywords.
     *
     * @param keywords List of keywords to check.
     * @return True if task's description matches any keywords.
     */
    public boolean isMatched(String... keywords) {
        return Arrays.stream(keywords).anyMatch(keyword -> this.description.contains(keyword));
    }

    /**
     * Sets the component of this task to a new component description.
     *
     * @param component Component of the task to be updated.
     * @param detail The new detail of the component.
     */
    public abstract void setComponent(TaskComponent component, Object detail) throws DukeException;
}

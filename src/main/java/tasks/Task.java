package tasks;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task to be completed.
 */
public abstract class Task {
    // Description of the task.
    protected String description;
    // Whether the task is done or not.
    protected boolean isDone;

    // Formatters to parse date time strings.
    public static DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static DateTimeFormatter outputDateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    /**
     * Constructs a new task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets a task as complete or uncomplete.
     *
     * @param done Whether to set the task as complete or uncomplete.
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Get a human readable representation of whether the task is complete or not.
     *
     * @return A human readable representation of whether the task is complete or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Represents the task in a string suitable for storing in memory.
     *
     * @return A string representing the task suitable for storing in memory.
     */
    abstract public String toEncodedString();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}

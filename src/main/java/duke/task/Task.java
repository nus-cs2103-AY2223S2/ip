package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Task class to record the user task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Constructor for the Task class.
     * @param description the description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Represents whether the Task is done.
     * @return String representation of whether the Task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done duke.task with X
    }

    /**
     * Gets the description of Task.
     * @return the description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of Task.
     * @param newDescription the new description of the Task.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Marks the Task as either done or not done yet.
     * @param marked boolean value on whether to mark the Task as done.
     */
    public void markUnmark(boolean marked) {
        this.isDone = marked;
    }
}

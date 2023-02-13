package duke.task;

import java.time.LocalDate;

/**
 * Class for Task object to encapsulate commonalities in subclasses.
 *
 * @author Andre Lin HuiKai
 * @version CS2103T AY22/23 Semester 2
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Task object.
     * @param des Description of the task.
     */
    public Task(String des) {
        this.description = des;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as incomplete.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Checks if a task is marked as done.
     * @return boolean value.
     */
    public boolean isComplete() { return this.isDone; }

    /**
     * Displays formatted information about the task.
     * @return String containing common information about a task.
     */
    public String getStatusIcon() {
        return this.isDone ? "[X] " + this.getDescription() : "[] " + this.getDescription();
    }

    /**
     * Gets the description of the task.
     * @return Description of the task in string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Encodes information about the task to be saved.
     * @return String representation of the encoded information.
     */
    public String encode() {
        return String.format("%s ### %s", this.isDone, this.description);
    }

    /**
     * Checks if a task falls on a given date.
     * @param date Date to check against.
     * @return boolean value that checks if the task falls on the specified date.
     */
    public boolean fallsOnDate(LocalDate date) {
        return false;
    }

    /**
     * Checks if a task is marked as complete before a specified date.
     * @param date the date to check against.
     * @return a boolean value.
     */
    public boolean isIncompleteBeforeDate(LocalDate date) {
        return false;
    }
}

package duke.task;

import java.time.LocalDate;

/**
 * An abstract Task class encapsulating a task in Duke, which can be extended
 * by more specific tasks like Events, toDos, etc.
 */
public abstract class DukeTask {
    private static final String DONE_FLAG = "[X] ";
    private static final String UNDONE_FLAG = "[ ] ";
    private String information;
    private final TaskType type;
    private boolean isDone;

    /**
     * Constructor for DukeTask that takes in the information of the task and its type.
     *
     * @param info The information of the task
     * @param type The type of the task
     */
    public DukeTask(String info, TaskType type) {
        this.information = info;
        this.type = type;
        this.isDone = false;
    }

    /**
     * Gets the information of the current task.
     *
     * @return the information of the current task
     */
    public String getInformation() {
        return this.information;
    }
    /**
     * Gets the type of the current task.
     *
     * @return the type of the current task
     */
    public TaskType getType() {
        return this.type;
    }

    /**
     * Check whether the task is done.
     *
     * @return Whether the task is done
     */
    public boolean getStatus() {
        return this.isDone;
    }
    /**
     * Mark the current task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * mark the current task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Forms the String representation for the storage file.
     *
     * @return the String representation for the storage file.
     */
    public abstract String storageString();

    /**
     * Takes in a date and check whether the current task happens on that date.
     *
     * @param date The given date to be checked
     * @return Whether the current task happens on that date
     */
    public abstract boolean matchesDate(LocalDate date);

    /**
     * Takes in a description and check whether the current task information that matches the given description.
     *
     * @param description The given description to be checked
     * @return Whether the description and the information matches
     */
    public boolean matches(String description) {
        return this.information.toUpperCase().contains(description.toUpperCase());
    }

    /**
     * Update the information of the task with the given string.
     *
     * @param information the new information of the task
     */
    public void updateInformation(String information) {
        this.information = information;
    }

    /**
     * Returns a string representation of the task in the format "DONE_FLAG/UNDONE_FLAG information".
     * DONE_FLAG is used if the task is done, UNDONE_FLAG otherwise.
     *
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        return (getStatus() ? DONE_FLAG : UNDONE_FLAG) + getInformation();
    }
}

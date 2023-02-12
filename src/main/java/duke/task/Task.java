package duke.task;

import duke.storage.Storage;

/**
 * Represents a task.
 */
public abstract class Task {
    private static final String DONE_ICON = "X";
    private static final String NOT_DONE_ICON = " ";

    protected String description;
    protected boolean isDone = false;
    private String eventSymbol;

    /**
     * Creates a task.
     *
     * @param description The description of a task.
     * @param eventSymbol The symbol for the type of task.
     */
    public Task(String description, String eventSymbol) {
        this.description = description;
        this.eventSymbol = eventSymbol;
    }

    /**
     * Returns the icon for the completion status of the task.
     *
     * @return "X" if the task is done, else " ".
     */
    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? DONE_ICON : NOT_DONE_ICON);
    }

    /**
     * Sets the task's completion status as done or undone.
     *
     * @param value The new status of the task.
     */
    public void setDone(boolean value) {
        isDone = value;
    }

    /**
     * Returns true if this task's description contains the keyword, false otherwise.
     *
     * @param keyword A keyword, which can contain spaces.
     * @return True if this task's description contains the keyword, false otherwise.
     */
    public boolean matchesKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns true if the days to the task from now is within the given days, false otherwise.
     *
     * @param maxDays Number of days to the task from now in which this method should return true.
     * @return True if the days to the task from now is within the given days, false otherwise.
     */
    public abstract boolean isUpcoming(long maxDays);

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + eventSymbol + "]" + "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string representation of the task for saving. It can be
     * parsed to create a task.
     *
     * @param storage A storage object to be used in formatting time.
     * @return A string representation of the task for saving.
     */
    public String toSaveString(Storage storage) {
        return eventSymbol + "|" + isDone + "|" + description;
    }

}

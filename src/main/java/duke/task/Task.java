package duke.task;

/**
 * Represents a task.
 */
public abstract class Task {
    /** Use to divide the fields of the task when writing to storage. */
    protected static final char FIELD_DIVIDER = '|';

    /** Use to split a task storage string into it's fields.*/
    static final String FIELD_SPLIT_REGEX = String.format(" \\%c ", Task.FIELD_DIVIDER);

    private static final String FIELD_DIVIDER_SUBSTITUTE = "\\|";

    private boolean isDone;
    private final String description;

    /**
     * Creates a Task object.
     *
     * @param isDone Is the task done.
     * @param description Description of the task.
     */
    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns a copy of this Task object with isDone set to the specified value.
     *
     * @param isDone The value to set isDone to.
     * @return Copy of this Task object with isDone set to the specified value.
     */
    public Task setDone(boolean isDone) {
        Task task = createCopy();
        task.isDone = isDone;

        return task;
    }

    @Override
    public String toString() {
        String doneStatusStr = isDone ? "X" : " ";

        return String.format("[%s] %s", doneStatusStr, description);
    }

    /**
     * Returns a string containing data about the task. This string is formatted for writing to storage.
     *
     * @return A string containing data about the task.
     */
    public String getStorageStr() {
        return String.format("%b %s %s", isDone, FIELD_DIVIDER, formatStrForStorage(description));
    }

    /**
     * Formats a string to be suited for writing to storage and returns the formatted string.
     *
     * @param str The string to be formatted.
     * @return The string formatted to be suited for writing to storage.
     */
    protected static String formatStrForStorage(String str) {
        assert str != null;

        return str.replace(Character.toString(FIELD_DIVIDER), FIELD_DIVIDER_SUBSTITUTE);
    }

    /**
     * Takes an array of strings that were loaded from storage, undo the formatting from
     * {@link duke.task.Task#formatStrForStorage}, and return the strings.
     *
     * @param strs The strings that are to be formatted.
     * @return The strings with formatting from {@link duke.task.Task#formatStrForStorage} undone.
     */
    protected static String[] formatStrsFromStorage(String[] strs) {
        assert strs != null;

        String[] formatted = new String[strs.length];

        for (int i = 0; i < strs.length; ++i) {
            formatted[i] = strs[i].replace(FIELD_DIVIDER_SUBSTITUTE, Character.toString(FIELD_DIVIDER));
        }

        return formatted;
    }

    /**
     * Returns true if the task is done. Otherwise, returns false.
     *
     * @return True if the task is done. False otherwise.
     */
    protected boolean isDone() {
        return isDone;
    }

    /**
     * Creates a copy of this task and returns it.
     *
     * @return A copy of this task.
     */
    protected abstract Task createCopy();
}

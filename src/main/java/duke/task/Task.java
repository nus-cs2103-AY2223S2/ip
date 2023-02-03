package duke.task;

/**
 * Represents a task.
 */
public abstract class Task {
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
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }

    /**
     * Returns a string containing data about the task. This string is formatted for writing to storage.
     *
     * @return A string containing data about the task.
     */
    public String getStorageStr() {
        return String.format("%s | %s", isDone, formatStrForStorage(description));
    }

    /**
     * Formats a specified string so that it is suited for writing to storage. Returns the formatted string.
     *
     * @param string The string to be formatted.
     * @return The string formatted to be suited for writing to storage.
     */
    protected static String formatStrForStorage(String string) {
        assert string != null;

        return string.replace("|", "\\|");
    }

    /**
     * Takes an array of strings that were loaded from storage, undo the formatting from
     * {@link duke.task.Task#formatStrForStorage}, and return the strings.
     *
     * @param strings The strings that are to be formatted.
     * @return The strings with formatting from {@link duke.task.Task#formatStrForStorage} undone.
     */
    protected static String[] formatStrsFromStorage(String[] strings) {
        assert strings != null;

        String[] formatted = new String[strings.length];

        for (int i = 0; i < strings.length; ++i) {
            formatted[i] = strings[i].replace("\\|", "|");
        }

        return formatted;
    }

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

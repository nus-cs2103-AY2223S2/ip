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

    public Task setDone(boolean isDone) {
        Task task = createCopy();
        task.isDone = isDone;

        return task;
    }

    /**
     * Returns a string containing data about the task. This string is formatted for writing to storage.
     *
     * @return A string containing data about the task.
     */
    public String getStorageString() {
        return String.format("%s | %s", isDone, formatStringForStorage(description));
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }

    /**
     * Returns a specified string formatted to be suited for writing to storage.
     *
     * @param string The string to be formatted.
     * @return The string formatted to be suited for writing to storage.
     */
    protected static String formatStringForStorage(String string) {
        return string.replace("|", "\\|");
    }

    /**
     * Takes an array of strings that was loaded from storage, undo the formatting from
     * {@link duke.task.Task#formatStringForStorage}, and return the strings.
     *
     * @param strings The strings that were loaded from storage and are to be formatted.
     * @return The strings with formatting from {@link duke.task.Task#formatStringForStorage} undone.
     */
    protected static String[] formatStringsFromStorage(String[] strings) {
        String[] formatted = new String[strings.length];

        for (int i = 0; i < strings.length; ++i) {
            formatted[i] = strings[i].replace("\\|", "|");
        }

        return formatted;
    }

    protected boolean isDone() {
        return isDone;
    }

    protected String getDescription() {
        return description;
    }

    /**
     * Creates a copy of this task and returns it.
     *
     * @return A copy of this task.
     */
    protected abstract Task createCopy();
}

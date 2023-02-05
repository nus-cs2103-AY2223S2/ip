package duke.task;

import duke.enums.TaskType;
/**
 * Represents a task of the user.
 *
 * @author wz2k
 */
public abstract class Task {
    /** Task description */
    private String description;

    /** Marking to show if task is done */
    private boolean isMarked;

    /** Type of task */
    private TaskType type;

    /**
     * Creates a task.
     *
     * @param description Description of the task.
     * @param isMarked Task marking.
     */
    public Task(String description, boolean isMarked, TaskType type) {
        this.description = description;
        this.isMarked = isMarked;
        this.type = type;
    }

    /**
     * Returns the symbol to show if the task is done or not.
     *
     * @return Task marked symbol.
     */
    private String getMarkedSymbol() {
        return isMarked ? "X" : " ";
    }

    /**
     * Returns the task checkbox and description.
     *
     * @return Task details.
     */
    public String toString() {
        return "[" + getMarkedSymbol() + "] " + description;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isMarked = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        isMarked = false;
    }

    /**
     * Returns the string to store the task in a file.
     *
     * @return File storage version of the task.
     */
    public String toTaskStorageString() {
        String marked = String.valueOf(isMarked);
        return marked + "|" + description;
    }

    /**
     * Checks if the given text is a substring of the task description.
     *
     * @param text Search string.
     * @return True if the text is part of the description and false otherwise.
     */
    public boolean hasSubstring(String text) {
        return description.contains(text);
    }

    /**
     * Gets the type of the task.
     *
     * @return Task type.
     */
    public TaskType getTaskType() {
        return type;
    }
}

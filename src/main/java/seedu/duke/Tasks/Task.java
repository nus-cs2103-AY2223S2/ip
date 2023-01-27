package seedu.duke.tasks;

import seedu.duke.DukeException;

public abstract class Task {
    protected final String description;
    protected final boolean isDone;
    protected final String taskType;

    /**
     *  Constructor for Task
     *
     *  @param description String description of the task
     *  @param isDone whether the task has been marked
     *  @param taskType String representing the type of task
     */
    protected Task(String description, boolean isDone, String taskType) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    /**
     * Gets the status checkbox
     *
     * @return String of the status checkbox
     */
    public String getStatusCheckbox() {
        return (this.isDone) ? "[X]" : "[]";
    }

    /**
     * Gets the boxed Task type
     *
     * @return String of the boxed Task type
     */
    public String getTaskTypeBox() {
        return String.format("[%s]", this.taskType);
    }

    /**
     * Marks a marked Task
     *
     * @return the new marked Task
     * @throws DukeException if Task is already marked
     */
    abstract public Task markTask() throws DukeException;

    /**
     * Marks an unmarked Task
     *
     * @return the new unmarked Task
     * @throws DukeException if Task is already unmarked
     */
    abstract public Task unmarkTask() throws DukeException;

    /**
     * Formats the Task into a format readable by Storage
     *
     * @return String of the formatted Task
     */
    abstract public String formatTask();

    @Override
    public String toString() {
        return this.description;
    }
}

package duke.task;

import duke.storage.Storable;

/**
 * Represents a Task.
 * Abstract - cannot be instantiated; serves as template.
 * Have to be Storable
 */
public abstract class Task implements Storable {

    private String description;
    private boolean isDone;

    /**
     * Constructs a Task with specified description.
     *
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with specified task status and description.
     *
     * @param isDone Status of the task (completed or not completed)
     * @param description Description of task
     */
    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Returns description of this Task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns task status of this Task. (Completed/Uncompleted)
     *
     * @return task status.
     */
    boolean isDone() {
        return isDone;
    }

    /**
     * Updates the task status with specified status.
     *
     * @param isDone Has the task been completed?
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toCsv() {
        return (isDone ? "true" : "false") + "," + description;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}


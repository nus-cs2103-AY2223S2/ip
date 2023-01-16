package duke.task;

/**
 * An abstract Task class encapsulating a task in Duke, which can be extended
 * by more specific tasks like Events, toDos, etc.
 */

public abstract class DukeTask {
    private final String information;
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
     * Forms the String representation for the storage file.
     *
     * @return the String representation for the storage file.
     */
    public abstract String storageString();

    @Override
    public String toString() {
        if (getStatus()) {
            return "[X] " + this.information;
        } else {
            return "[ ] " + this.information;
        }
    }
}

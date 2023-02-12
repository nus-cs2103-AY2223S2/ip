package duke;

/**
 * Tasks is an object that is to be done or undertaken by the user
 */
public class Tasks {

    /**
     * description of the task
     */
    protected String description;
    /**
     * boolean that indicates whether a task has been completed
     */
    protected boolean isDone;

    /**
     * returns a tasks object with a description that is marked undone
     * @param description Describes the task
     */
    public Tasks(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Saves the task in data/duke.txt
     * @return a string that will be saved
     */
    public String log() {
        if (this.isDone) {
            return " | 1 | " + this.description;
        } else {
            return (" | 0 | " + this.description);
        }
    }

    /**
     * Returns the description of the task
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task, "X" indicates done,
     * " " indicates undone
     * @return Status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Represents the task as a string
     * @return String that represents the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

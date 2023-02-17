package duke.task;

/**
 * Class for Task object.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task object.
     *
     * @param description
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Displays whether the task is completed or not.
     *
     * @return String informing users on whether the task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns message containing information about the task.
     *
     * @return String representing the task information.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsIncomplete() {
        this.isDone = false;
    }

    public String getTaskType() {
        return "";
    }

    public void editInfo(String content) {
        this.description = content;
    }
}


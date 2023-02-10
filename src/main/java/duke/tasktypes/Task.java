package duke.tasktypes;

import java.time.LocalDateTime;

/**
 * Abstract representation of a Task object.
 */
public abstract class Task {

    /** ID Counter to assign unique IDs to tasks */
    protected static int taskIdCounter = 0;
    /** Description of a task */
    protected String description;
    /** Indicated whether a task is completed */
    protected boolean isDone;
    /** Assigned ID for task */
    protected int taskID;
    /** Deadline of a task */
    protected LocalDateTime when;

    /**
     * Constructor to initialize a Task.
     * To be called by the concrete Child classes of Task.
     *
     * @param description A description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskID = taskIdCounter++;
    }

    /**
     * Mark a task as completed.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Mark a task as incomplete.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Returns a String representation of a Task.
     * Appends the completed status of the task to the front.
     *
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        String status;
        if (this.isDone) {
            status = "[X]";
        } else {
            status = "[ ]";
        }
        return status + " " + this.description + " #" + this.taskID;
    }

    /**
     * Returns a String representation of Task that is compatible with Saving and Loading.
     *
     * @return String representation of Task in Save Format.
     */
    public abstract String getSaveFormat();

    /**
     * Returns true if description contains at least one keyword. False otherwise.
     *
     * @param keywords String array containing keywords.
     * @return Boolean variable indicating match result.
     */
    public boolean matchKeywords(String[] keywords) {
        boolean isContained = false;
        for (String s : keywords) {
            if (this.description.contains(s)) {
                isContained = true;
                break;
            }
        }
        return isContained;
    }

    public int getTaskID() {
        return this.taskID;
    }

    public LocalDateTime getDeadline() {
        return this.when;
    }
    public static void resetCounter() {
        Task.taskIdCounter = 0;
    }
}


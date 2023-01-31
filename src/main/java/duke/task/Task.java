package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a task added by the user.
 * It has a description attached to it and a isDone status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class, used to set description and status of task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns X if task is marked done.
     * If task is unmarked, a space is returned.
     *
     * @return Character to indicate the status of the task.
     */
    public char getStatusIcon() {
        return (isDone) ? 'X' : ' ';
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the String representation of a Task.
     *
     * @return String representation of a Task in this format: [{status}] {description}.
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Returns the raw String representation of a Task to be stored in the local file for storage.
     *
     * @return Raw String representation of a Task in this format: T ~ {status} ~ {description}.
     */
    public String getRawTask() {
        return String.format("T ~ %d ~ %s\n", isDone ? 1 : 0, this.description);
    }

    /**
     * Returns warning message if the date time of the task is expired or expiring soon.
     *
     * @param dateTime Date time of the task.
     * @return Warning message to be printed to user.
     */
    public String urgentMessage(LocalDateTime dateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int diff = currentDateTime.compareTo(dateTime);
        if (diff > 0) {
            return "⚠ TASK EXPIRED! ⚠";
        } else if (diff == -1) {
            return "⚠ TASK EXPIRING SOON! ⚠";
        } else {
            return "";
        }
    }
}

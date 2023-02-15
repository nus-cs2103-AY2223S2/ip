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
     * @param input for Task.
     */
    public Task(String input) {
        description = input;
        this.isDone = false;
    }

    public LocalDateTime getDate() {
        // does nothing
        return null;
    }

    protected char getStatusIcon() {
        return (isDone) ? 'X' : ' ';
    }

    protected String getDescription() {
        return this.description;
    }

    public String getRawTask() {
        return String.format("T ~ %d ~ %s\n", isDone ? 1 : 0, description);
    }

    /**
     * Returns the String representation of a Task.
     *
     * @return String representation of a Task in this format: [{status}] {description}.
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", getStatusIcon(), description);
    }

    /**
     * Returns warning message if the date time of the task is expired or expiring soon, within a week.
     *
     * @param dateTime of the task.
     * @return Warning message to be printed to user.
     */
    protected String getUrgentMessage(LocalDateTime dateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime sevenDaysAhead = currentDateTime.plusDays(7);
        if (currentDateTime.compareTo(dateTime) > 0) {
            return "\n!! TASK EXPIRED! !!";
        } else if (sevenDaysAhead.compareTo(dateTime) > 0) {
            return "\n!! TASK EXPIRING WITHIN A WEEK !!";
        } else {
            return "";
        }
    }

    /**
     * Marks task as done.
     */
    protected void mark() {
        isDone = true;
    }

    /**
     * Unmarks task as undone.
     */
    protected void unmark() {
        isDone = false;
    }

    /**
     * Marks task if it has a status of 1.
     *
     * @param taskStatus of the task in String.
     * @param task to be marked if needed.
     */
    protected void markTaskIfNeeded(String taskStatus, Task task) {
        boolean isTaskStatusUnmarkValid = taskStatus.equals("0");
        boolean isTaskStatusMarkValid = taskStatus.equals("1");
        assert isTaskStatusUnmarkValid || isTaskStatusMarkValid : "Task Status must be 0 or 1";
        if (isTaskStatusMarkValid) {
            task.mark();
        }
    }
}

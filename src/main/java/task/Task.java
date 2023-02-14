package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task component abstract class.
 */
public abstract class Task {
    private String task;
    private boolean isCompleted;

    /**
     * Task constructor.
     *
     * @param task a task string
     * @param isCompleted the completion status
     */
    public Task(String task, boolean isCompleted) {
        this.task = task;
        this.isCompleted = isCompleted;
    }

    /**
     * Retrieves the task string.
     *
     * @return A task string.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Retrieves the completion status.
     *
     * @return Whether the task is completed.
     */
    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    /**
     * Toggles todo as completed.
     */
    public void markCompleted() {
        this.isCompleted = true;
    }

    /**
     * Toggles todo as uncompleted.
     */
    public void unmarkCompleted() {
        this.isCompleted = false;
    }

    /**
     * Returns the formatted string to store in datafile.
     *
     * @return A formatted string for storage in datafile.
     */
    public abstract String toDataString();

    public String getFormattedStatus() {
        return isCompleted ? "[X]" : "[ ]";
    }

    public String getFormattedDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }

        Task task1 = (Task) o;

        if (isCompleted != task1.isCompleted) {
            return false;
        }
        return task.equals(task1.task);
    }
}

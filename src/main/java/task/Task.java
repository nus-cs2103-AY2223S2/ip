package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    String task;
    boolean isCompleted;

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

    public String formattedStatus() {
        return isCompleted ? "[X]" : "[ ]";
    }

    public String formattedDate(LocalDate date) {
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

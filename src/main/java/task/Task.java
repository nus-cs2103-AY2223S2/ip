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

    public String formattedStatus() {
        return isCompleted ? "[X]" : "[ ]";
    }

    public String formattedDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}

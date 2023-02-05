package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime dueDate;

    /**
     * Constructor method for Deadline
     * @param task new task to be added
     * @param dueDate deadline of the task
     */
    public Deadline(String task, LocalDateTime dueDate) {
        super(task);
        this.dueDate = dueDate;
    }

    @Override
    public String toData() {
        String status = this.completed ? "1" : "0";
        return "D | " + status + " | " + this.task + " | " + this.dueDate;
    }

    @Override
    public String toString() {
        String statusIcon = this.completed ? "X" : " ";
        String formattedDueDate = dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
        return "[D][" + statusIcon + "] " + this.task + " (by: " + formattedDueDate + ")";
    }
}

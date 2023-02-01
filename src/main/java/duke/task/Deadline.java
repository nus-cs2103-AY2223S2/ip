package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDate dueDate;

    /**
     * Constructor method for Deadline
     * @param task new duke.task to be added
     * @param dueDate deadline of the duke.task
     */
    public Deadline(String task, LocalDate dueDate) {
        super(task);
        this.dueDate = dueDate;
    }

    /**
     * Convert the Deadline duke.task into data from for duke.storage into file.
     * @return String representation of the data.
     */
    public String toData() {
        String status = this.completed ? "1" : "0";
        return "D | " + status + " | " + this.task + " | " + this.dueDate;
    }
    @Override
    public String toString() {
        String statusIcon = this.completed ? "X" : " ";
        String formattedDueDate = dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyy"));
        return "[D][" + statusIcon + "] " + this.task + " (by: " + formattedDueDate + ")";
    }
}

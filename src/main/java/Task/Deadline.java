package Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
//    final private String deadline;
    final private LocalDate dueDate;

    /**
     * Constructor method for Deadline
     * @param task new task to be added
     * @param dueDate deadline of the task
     */
    public Deadline(String task, LocalDate dueDate) {
        super(task);
        this.dueDate = dueDate;
    }

    public String toData() {
        String status = this.completed ? "1" : "0";
        return "D | " + status + " |" + this.task + "| " + this.dueDate;
    }
    @Override
    public String toString() {
        String statusIcon = this.completed ? "X" : " ";
        String formatted_dueDate = dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyy"));
        return "[D][" + statusIcon + "]" + this.task + "(by: " + formatted_dueDate + ")";
    }
}

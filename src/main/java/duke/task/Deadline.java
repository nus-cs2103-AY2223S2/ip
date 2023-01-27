package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task subclass to represent a Deadline (due date).
 */
public class Deadline extends Task{
    private LocalDate dueDate;

    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String getDescription() {
        return this.description + " (by: " + this.dueDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}

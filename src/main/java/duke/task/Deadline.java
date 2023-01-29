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
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Deadline) {
            Deadline other = (Deadline) obj;
            return this.description.equals(other.description)
                    && this.dueDate.equals(other.dueDate);
        }
        return false;
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

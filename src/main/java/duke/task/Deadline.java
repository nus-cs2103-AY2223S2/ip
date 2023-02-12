package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A task subclass to represent a Deadline (due date).
 */
public class Deadline extends Task {
    /** Due-date of the task. */
    private LocalDate dueDate;

    /**
     * Constructor for Deadline class.
     * @param description The deadline details.
     * @param dueDate The LocalDate for the deadline in "yyyy-mm-dd" format.
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Constructor for Task class.
     * @param description The task details.
     * @param dueDate The LocalDate for the deadline in "yyyy-mm-dd" format.
     * @param tags Tags for the task.
     */
    public Deadline(String description, LocalDate dueDate, ArrayList<String> tags) {
        super(description, tags);
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

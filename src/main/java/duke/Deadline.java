package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class
 */
public class Deadline extends Task {
    LocalDate dueDate;

    /**
     * Constructor for instantiating a deadline object
     * @param description description of the event
     * @param dueDate due date of the event
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Returns the string representation of the deadline object
     * @return String string representation of the deadline, which includes type of task, completion status, task
     * description and due date
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + this.dueDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ')';
    }
}

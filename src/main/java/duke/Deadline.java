package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class
 */
public class Deadline extends Task {
    private static final String TASK_TYPE = "[D]";
    private LocalDate dueDate;

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

    /**
     * Returns the string representation of the deadline object to be stored in the data.txt file
     * @return the string representation with dividers of the deadline object to be stored in the data.txt file
     */
    @Override
    public String toStorageData() {
        String completionStatus = this.getStatusIcon();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dueDateString = this.dueDate.format(dateFormatter);
        return TASK_TYPE + DIVIDER + completionStatus + DIVIDER + super.description + DIVIDER + dueDateString;
    }
}

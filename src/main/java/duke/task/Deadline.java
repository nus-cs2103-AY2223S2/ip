package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a type of Task.
 * All objects of class Deadline have a 'by' field to indicate the period the task
 * must be completed by.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate date;


    /**
     * Constructs a Deadline task with the given name, and the 'by' field.
     *
     * @param description The description of the Deadline Task.
     * @param by The deadline to finish said task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = LocalDate.parse(by);
    }

    /**
     * Returns the string representation of the Deadline object.
     *
     * @return The name of this task and the details of the timing.
     */
    @Override
    public String toString() {
        String finDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + "(by: " + finDate + ")";
    }
}

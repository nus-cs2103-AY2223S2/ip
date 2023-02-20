package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a Deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Parameterized constructor to create a Deadline
     * @param description description of the task to be created
     * @param by the deadline the task is to be completed by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Parameterized constructor to create a Deadline
     * @param description description of the task to be created
     * @param isDone if the task has been completed
     * @param by the deadline the task is to be completed by
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the String representation of a Deadline which can be displayed to the user
     * @return the String representation of a Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the String representation of a Deadline which can be written to a file
     * @return the String to be written to a file
     */
    @Override
    public String toFile() {
        return "D " + super.toFile() + " /by: " + by;
    }
}


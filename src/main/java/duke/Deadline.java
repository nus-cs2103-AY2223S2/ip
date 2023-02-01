package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a task which has a specific deadline.
 *
 * @author Sean Chin Jun Kai
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for creating a Deadline object.
     *
     * @param description name of the task.
     * @param by deadline of this task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns string representation of a Deadline object which users can see in the command line.
     *
     * @return String representation of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")\n";
    }

    /**
     * Returns string representation of a Deadline object to store in txt file.
     *
     * @return String representation of deadline.
     */
    @Override
    public String getText() {
        return "D " + super.getText()
                + " | " + by;
    }
}

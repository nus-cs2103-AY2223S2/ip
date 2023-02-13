package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for Deadline object which encapsulates information and due date of this Task.
 *
 * @author Eric Leow Yu Quan
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for Deadline instance.
     *
     * @param description a simple description of this Deadline Task.
     * @param by a string indicating the time or date this item is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the string representation of a Deadline instance in MMM d yyyy format.
     *
     * @return the desired string representation of a Deadline instance.
     */
    @Override
    public String toString() {
        String dateFormat = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + dateFormat + ")";
    }

    /**
     * Returns the string representation of a Deadline instance in YYYY-MM-DD format.
     *
     * @return the desired string representation of a Deadline instance.
     */
    public String parse() {
        return "[D]" + super.parse() + "(by: " + by + ") " + super.addOn();
    }
}

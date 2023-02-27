package duke.task;

import duke.exception.DescriptionException;
import duke.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * Represents a deadline object
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    protected String strBy;

    /**
     * Constructor for a deadline object based on the attributes that it has
     * @param description string representing the description
     * @param by due datetime in string
     * @throws DescriptionException If description is empty
     */
    public Deadline(String description, String by) throws DescriptionException {
        super(description);
        this.strBy = by;
        this.by = getLocalDateTime(this.strBy);
        if (this.by != null) {
            this.strBy = this.by.format(DateTimeFormat.defaultOutput.formatter);
        }
    }

    /**
     * String representation of the object
     * @return String representation of deadline
     */
    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + this.strBy;
    }
}

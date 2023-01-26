package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the string representation of a Deadline instance in MMM d yyyy format.
     * @return the desired string representation of a Deadline instance.
     */
    @Override
    public String toString() {
        String dateFormat = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + dateFormat + ")";
    }

    /**
     * Returns the string representation of a Deadline instance in YYYY-MM-DD format.
     * @return the desired string representation of a Deadline instance.
     */
    public String parse() {
        return "[D]" + super.parse() + " (by: " + by + ")";
    }
}

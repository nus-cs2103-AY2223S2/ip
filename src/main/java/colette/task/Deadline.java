package colette.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** Class that represents a deadline */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructs a Deadline object with
     * the given name and a date
     * to do this task by.
     *
     * @param name Name of this deadline.
     * @param by Date to do this task by.
     * @throws DateTimeParseException if by is not a validly formatted date.
     */
    public Deadline(String name, String by) throws DateTimeParseException {
        super(name);
        this.by = LocalDate.parse(by);
        assert this.by != null : "By should not be null";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: "
            + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getFileRepresentation() {
        return "D" + "@" + (super.isDone() ? "1" : "0") + "@" + this.getName() + "@" + this.by;
    }

}

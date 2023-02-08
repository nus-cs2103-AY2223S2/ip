package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private DateTimeFormatter formatterToString = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

    /**
     * Constructor for a Deadline task.
     *
     * @param description Description of the task.
     * @param by Due date and time of the task in yyyy-MM-dd HH:mm format.
     * @throws DateTimeParseException If deadline cannot be parsed into a LocalDateTime object.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDateTime.parse(by, formatter);
    }

    /**
     * Returns deadline of the task as a string.
     *
     * @return Deadline as a String object.
     */
    public String getBy() {
        return by.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatterToString) + ")";
    }
}

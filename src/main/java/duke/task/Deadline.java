package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents Deadline task with a description and a deadline.
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructor for deadline. Could throw DateTimeParseException which is handled in AddDeadlineCommand instead.
     * @param description for the task
     * @param by deadline date as a string
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toTXT() { return "D | " + super.toTXT() + " | " + by; }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

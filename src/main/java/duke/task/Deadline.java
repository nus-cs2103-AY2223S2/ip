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
    public String toTXT() {
        return String.format("D | %s | %s", super.toTXT(), by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}

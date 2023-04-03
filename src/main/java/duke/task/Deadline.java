package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    /** Date of this Deadline */
    protected LocalDate by;

    /**
     * A constructor to initialize a Deadline.
     *
     * @param by The date of this Deadline.
     * @param desc The description of this Deadline.
     */
    public Deadline(LocalDate by, String desc) {
        super(desc);
        this.by = by;
    }

    @Override
    public String toString() {
        String priority = this.getPrioritySign();
        return priority + " [D]" + super.toString() + " (by: " + this.by.format(FORMATTER) + ") "
                + this.tagsToString();
    }
}

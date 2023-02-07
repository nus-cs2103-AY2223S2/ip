package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Deadline.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Instantiates a new Deadline.
     *
     * @param description the description
     * @param by          the by
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + " " + this.description
                + " (by:" + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toSaveableString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, by);
    }
}

package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class is a deadline task. It contains
 * the description of the task and the time
 * the task is due.
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructs a deadline with the given description and date.
     *
     * @param description Description of the deadline task.
     * @param by The deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a deadline with the given description, isDone and date.
     *
     * @param description Description of the deadline task.
     * @param isDone Whether the deadline task is done.
     * @param by The deadline.
     */
    public Deadline(String description, boolean isDone, LocalDate by , ArrayList<String> tags) {
        super(description, isDone, tags);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s  (by: %s) %s",
                super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.getStringOfTags());
    }

    @Override
    public String convertToStorableString() {
        return String.format("D|%s|%s|%s|%s",
                this.isDone() ? "1" : "0",
                this.getDescription(),
                this.by.toString(),
                this.getTags()
                );
    }
}

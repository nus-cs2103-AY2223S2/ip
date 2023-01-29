package fea.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that extends Task class.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Constructor method to initialise Deadline.
     * @param description The description of the deadline.
     * @param by The deadline time.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    private String parseDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        return dateTime.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), parseDateTime(this.by));
    }
}

package duke.packages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    public Deadline(String description, boolean isCompleted, LocalDate by) {
        super(description, isCompleted);
        this.by = by;
    }
    public LocalDate getDeadline() {
        return this.by;
    }
    @Override
    public String toString() {
        String byFormatted = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return "[D]" + super.toString() + " (by: " + byFormatted + ")";
    }
}
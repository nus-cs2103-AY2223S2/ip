package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, formatInput);
    }

    public LocalDate getDate() {
        return this.by.toLocalDate();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + by.format(formatOutput) + ")";
    }
}

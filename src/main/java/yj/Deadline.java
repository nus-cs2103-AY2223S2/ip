package yj;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

    public Deadline(String description, String by) {
        super(description);
        // Parse the by string as a LocalDateTime
        this.by = LocalDateTime.parse(by, dateTimeFormatter);
    }

    public String getBy() {
        return outputFormatter.format(by);
    }

    public void setBy(String by) {
        this.by = LocalDateTime.parse(by, dateTimeFormatter);
    }

    @Override
    public String toString() {
        // [D][ ] return book (by: June 6th)
        return "[D]" + " " + super.getStatusIcon() + " " + super.toString() + " (by: " + getBy() + ")";
    }

    @Override
    public String toSaveString() {
        // D | 0 | return book | June 6th
        return "D | " + (isDone ? 1 : 0) + " | " + super.toString() + " | " + by.format(dateTimeFormatter);
    }
}

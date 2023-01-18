package dude.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toRaw() {
        return "D | " + (this.isDone ? 1 : 0) + " | " + description + " | " + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + "\n";
    }
}
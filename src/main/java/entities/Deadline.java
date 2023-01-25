package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by.trim());
    }

    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String toSave() {
        if (super.isDone == true) {
            return String.format("D | 1 | %s | %s\n", super.getDescription(), this.by);
        }
        return String.format("D | 0 | %s | %s\n", super.getDescription(), this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

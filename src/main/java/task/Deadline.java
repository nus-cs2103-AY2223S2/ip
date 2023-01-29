package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + "(by " + by.format(formatter) + ")";
    }

    @Override
    public String toSavedString() {
        return "D" + "|" + (super.isDone ? "1" : "0")
                + "|" + super.description + "|" + this.by;
    }

}
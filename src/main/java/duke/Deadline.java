package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeParseException;
class Deadline extends Task {

    protected String by;     
    Deadline() {

    }
    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }
    String localDateParser() {
        try {
            LocalDate date = LocalDate.parse(by);
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            return by;
        }
    }
    @Override
    Deadline markAsDone() {
        return new Deadline(getDescription(), by, true);
    }
    @Override
    Deadline markAsUndone() {
        return new Deadline(getDescription(), by, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + localDateParser() + ")";
    }
}

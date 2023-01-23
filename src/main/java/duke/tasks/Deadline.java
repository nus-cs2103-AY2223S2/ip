package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String date) {
        super(description);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDateTime output = LocalDateTime.parse(date, dateTimeFormatter);
            DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            this.by = output.format(newPattern);
        } catch (DateTimeParseException e) {
            LocalDate output = LocalDate.parse(date, dateFormatter);
            DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
            this.by = output.format(newPattern);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}

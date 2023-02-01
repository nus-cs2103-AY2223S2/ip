package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " "
                + super.getDescription() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    public static Deadline addDeadline(String details) {
        String description = details.substring(0, details.indexOf(" /by"));
        String byString = details.substring(details.indexOf(" /by") + " /by".length() + 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime by = LocalDateTime.parse(byString, formatter);

        return new Deadline(description, by);
    }
}
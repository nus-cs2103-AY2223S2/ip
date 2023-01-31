package clippy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        // only show day of the week + day of the month + month
        return "[D]" + super.toString() +
                " (by: " + by.format(DateTimeFormatter.ofPattern("EEE dd MMM")) + ")";
    }

    @Override
    public String getCsvString() {
        return String.format("D,%s,%s", super.getCsvString(), this.by);
    }
}


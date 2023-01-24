package task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDate endDate;
    public Deadline(String task, String endDate) throws DateTimeParseException {
        super(task);
        this.endDate = LocalDate.parse(endDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

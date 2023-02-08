package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime endDate;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.endDate = by;
    }

    public String getByDate() {
        return this.endDate.toString();
    }
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        String dateTimeString = endDate.format(format);
        return "[D]" + super.toString() + " (by: " + dateTimeString + ")";
    }
}

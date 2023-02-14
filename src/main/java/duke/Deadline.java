package duke;

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
        String formattedByDate = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[D]" + super.toString() + " (by: " + formattedByDate + ")";
    }

    @Override
    public String formatForFile() {
        String formattedByDate = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        if (this.isDone) {
            return "D | " + 1 + " | " + description + " | " + formattedByDate + "\n";
        } else {
            return "D | " + 0 + " | " + description + " | " + formattedByDate + "\n";
        }
    }

    @Override
    public Type getType() {
        return Type.DEADLINE;
    }
}

package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    static DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(format) + ")";
    }

    public String getTypeIcon(){
        return "[D]";
    }
}

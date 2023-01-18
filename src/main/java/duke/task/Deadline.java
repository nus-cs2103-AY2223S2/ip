package duke.task;

import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DATE_FORMAT) + ")";
    }
}

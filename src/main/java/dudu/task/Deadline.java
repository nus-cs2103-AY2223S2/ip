package dudu.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    private final String name;
    private boolean isDone;
    private LocalDate by;
    public Deadline(String name, String by) {
        super(name, false);
        this.name = name;
        this.by = LocalDate.parse(by);
    }

    public Deadline(String name, String by, boolean isDone) {
        super(name, isDone);
        this.name = name;
        this.by = LocalDate.parse(by);
    }

    @Override
    public String getType() {
        return "Deadline";
    }

    @Override
    public String getStatus() {
        return isDone() ? "1" : "0";
    }

    @Override
    public String getDescription() {
        return name + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

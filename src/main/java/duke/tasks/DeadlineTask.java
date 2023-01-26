package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private final LocalDate by;

    public DeadlineTask(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    public boolean isDueOn(LocalDate date) {
        return by.equals(date);
    }

    public String toDukeFileString() {
        return "D|" + super.toDukeFileString() + "|" + by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedByString = by.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedByString + ")";
    }
}

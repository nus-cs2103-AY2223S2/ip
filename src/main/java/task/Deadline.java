package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    String dead;
    LocalDate deadDate;
    public Deadline(String details, String dead) {
        super(details);
        try {
            this.deadDate = LocalDate.parse(dead);
            this.dead = deadDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException ignored) {
            this.dead = dead;
        }
    }

    @Override
    public String toString() {
        String task = super.toString();
        return "[D] " + task + " (by: " + dead + ")";
    }

    @Override
    public String toStorageString() {
        return "D#" + super.toStorageString() + "#" + dead;
    }
}

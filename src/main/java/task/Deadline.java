package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    String dead;
    LocalDate deadDate;

    /**
     * Constructor for a new Deadline object. Also processes the date provided if written in the correct format.
     * @param details the specifics of what the deadline is for
     * @param dead when the deadline is
     */
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
        assert !task.equals("") && !dead.equals(""): "Error checker did not catch missing input";
        return "[D] " + task + " (by: " + dead + ")";
    }

    /**
     * Returns the details of the deadline in a format that can be stored
     *  in the file for easy loading later.
     * @return the string to store in the file
     */
    @Override
    public String toStorageString() {
        return "D#" + super.toStorageString() + "#" + dead;
    }
}

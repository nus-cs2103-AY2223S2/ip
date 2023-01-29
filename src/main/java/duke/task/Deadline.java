package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate by;

    public Deadline(String title, LocalDate by) {
        super(title);
        this.by = by;
    }

    /**
     * Return the display string representing the deadline
     * @return string form of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Return the saved string representing the deadline
     * @return saved string form of the deadline
     */
    @Override
    public String toSavedString() {
        return "D|" + super.toSavedString() + "|" + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}

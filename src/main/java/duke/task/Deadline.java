package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Returns deadline task.
     * @param description description of task.
     * @param by deadline of task.
     */
    public Deadline(String description, String by) {
        super(description.trim());
        this.by = LocalDate.parse(by.trim());
    }

    /**
     * Returns format of task for printing to user.
     * @return string format.
     */
    @Override
    public String toString() {
        String byStr = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        return "[D]" + super.toString() + " (by: " + byStr + ")";
    }

    /**
     * Return format of task for backup.
     * @return string format.
     */
    @Override
    public String toBackup() {
        return "D | " + super.toBackup() + " | " + by + "\n";
    }
}

package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Class constructor.
     * @param description Name of the Deadline task.
     * @param by Due date for the Deadline task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Print the Deadline task in desired format.
     * @return Deadline task printed in format "[D][] task name (by: yyyy/MM/dd HH:mm)".
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return "[D]" + super.getStatusIcon() + " (by: " + dateFormat.format(this.by) + ")";
    }

    /**
     * Save the Deadline task in desired format.
     * @return Deadline task saved in text file in format "D| |task name |yyyy/MM/dd HH:mm".
     */
    @Override
    public String toRecord() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return "D|" + super.toRecord() + "|" + dateFormat.format(this.by);
    }
}

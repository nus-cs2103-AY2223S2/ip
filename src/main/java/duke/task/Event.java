package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Returns event task.
     * @param description description of task.
     * @param from start time of task.
     * @param to end time of task.
     */
    public Event(String description, String from, String to) {
        super(description.trim());
        this.from = LocalDate.parse(from.trim());
        this.to = LocalDate.parse(to.trim());
    }

    /**
     * Returns format of task for printing to user.
     * @return string format.
     */
    @Override
    public String toString() {
        String fromStr = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String toStr = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        return "[E]" + super.toString() + "(from: " + fromStr + " to: " + toStr + ")";
    }

    /**
     * Return format of task for backup.
     * @return string format.
     */
    @Override
    public String toBackup() {
        return "D | " + super.toBackup() + " | " + from + " | " + to + "\n";
    }
}

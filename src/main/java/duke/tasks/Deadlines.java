package duke.tasks;

import java.time.LocalDateTime;

/**
 * Deadlines tasks tracks when the task has to be completed by.
 *
 * @author Cheam Jia wei
 */
public class Deadlines extends Task {
    private LocalDateTime end;

    /**
     * Constructor for Deadlines task.
     *
     * @param name Name of task.
     * @param end Date task has to be completed by
     */
    public Deadlines(String name, String end) {
        super(name);
        this.end = LocalDateTime.parse(end);
    }

    @Override
    public boolean isWithinDate(LocalDateTime date) {
        return date.isBefore(end);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + dateFormat(this.end) + ")";
    }

    @Override
    public String toWrite() {
        return "D | " + super.toWrite() + " | " + this.end + "\n";
    }
}

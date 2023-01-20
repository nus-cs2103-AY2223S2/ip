package duke.tasks;

import java.time.LocalDateTime;

/**
 * Events task tracks when the task starts and ends.
 *
 * @author Cheam Jia Wei
 */
public class Events extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for Events task.
     *
     * @param name Name of the task.
     * @param start Date of when the task starts.
     * @param end Date of when the task ends.
     */
    public Events(String name, String start, String end) {
        super(name);
        this.start = LocalDateTime.parse(start);
        this.end = LocalDateTime.parse(end);
    }

    @Override
    public boolean isWithinDate(LocalDateTime date) {
        return date.isBefore(end) && date.isAfter(start);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + dateFormat(this.start)
                + " to: " + dateFormat(this.end) + ")";
    }

    @Override
    public String toWrite() {
        return "E | " + super.toWrite() + " | " + this.start
                + " | " + this.end + "\n";
    }
}

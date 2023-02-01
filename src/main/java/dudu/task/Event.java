package dudu.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task class for event type
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    private String desc;

    /**
     * Constructor for event task, marked as undone.
     *
     * @param desc Description of the event task
     * @param from Starting date
     * @param to Ending date
     */
    public Event(String desc, String from, String to) {
        super(desc, false);
        this.desc = desc;
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Constructor for event task.
     *
     * @param desc Description of the event task
     * @param from Starting date
     * @param to Ending date
     * @param isDone Completing status of the task
     */
    public Event(String desc, String from, String to, boolean isDone) {
        super(desc, isDone);
        this.desc = desc;
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String getType() {
        return "Event";
    }

    @Override
    public String getStatus() {
        return isDone() ? "1" : "0";
    }

    @Override
    public String getDescription() {
        return desc + " | " + from + " | " + to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

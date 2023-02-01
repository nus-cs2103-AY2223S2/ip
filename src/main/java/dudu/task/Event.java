package dudu.task;

import dudu.exception.InvalidCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
    public Event(String desc, String from, String to) throws InvalidCommandException {
        this(desc, from, to, false);
    }

    /**
     * Constructor for event task.
     *
     * @param desc Description of the event task
     * @param from Starting date
     * @param to Ending date
     * @param isDone Completing status of the task
     */
    public Event(String desc, String from, String to, boolean isDone) throws InvalidCommandException {
        super(desc, isDone);
        this.desc = desc;
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException ex) {
            throw new InvalidCommandException(ex.getMessage(),
                    "The date format Wrong! Please follow following format: yyyy-MM-dd");
        }
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

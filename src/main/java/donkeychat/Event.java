package donkeychat;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * A task that contains a from date and to date.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates a new Deadline task.
     * @param description Description of the task.
     * @param from From date for the task.
     * @param to To date for the task.
     */
    public Event(String description, String from, String to) {
        this(description, false, from, to);
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        try {
            this.from = LocalDateTime.parse(from, FORMAT_DATE_TIME);
            this.to = LocalDateTime.parse(to, FORMAT_DATE_TIME);
        } catch (DateTimeParseException e) {
            System.out.println("Please use the correct format for dates, i.e. '12-10-2023 16:00'");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
            from.format(FORMAT_PRINT) +
            " to: " + to.format(FORMAT_PRINT) + ")";
    }

    @Override
    public String serialize() {
        return "E | " + super.serialize() + " | " + from.format(FORMAT_PRINT) + " | " + to.format(FORMAT_PRINT);
    }
}

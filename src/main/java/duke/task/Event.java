package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to support Event tasks.
 * Events are tasks that start at a specific date/time and end at a specific
 * date/time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");


    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, inputFormatter);
        this.to = LocalDateTime.parse(to, inputFormatter);
    }

    /**
     * Returns string representation of event begin date (in input format).
     * This method is to be used for internal use (not for output to user) such
     * as to save to storage.
     *
     * @return Event begin date formatted for subsequent input.
     */
    public String getFrom() {
        return this.from.format(inputFormatter);
    }

    /**
     * Returns string representation of event end date (in input format).
     * This method is to be used for internal use (not for output to user) such
     * as to save to storage.
     *
     * @return Event end date formatted for subsequent input.
     */
    public String getTo() {
        return this.to.format(inputFormatter);
    }

    /**
     * @inheritDoc
     *
     * @return "E".
     */
    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.toString() +
                " (from: " + from.format(outputFormatter) + " to: " + to.format(outputFormatter) + ")";
    }
}

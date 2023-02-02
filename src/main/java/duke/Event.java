package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private DateTimeFormatter formatterToString = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

    /**
     * Constructor for an Event task.
     *
     * @param description Description for the event task.
     * @param from Start date and time of the event.
     * @param to End date and time of the event.
     * @throws DateTimeParseException If start or end dates and times cannot be parsed
     * into LocalDateTime objects.
     */
    public Event(String description, String from, String to) throws DateTimeParseException {
        super(description);
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    /**
     * Returns start date and time of the event as a string.
     *
     * @return Start date and time as a String object.
     */
    public String getFrom() {
        return from.format(formatter);
    }

    /**
     * Returns end date and time of the event as a string.
     *
     * @return End date and time as a String object.
     */
    public String getTo() {
        return to.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(formatterToString)
                + " to: " + to.format(formatterToString) + ")";
    }
}

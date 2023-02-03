package page.quest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import page.PageException;

/**
 * Represents an event, i.e. a quest with a start and end date/time.
 */
public class Event extends Quest {
    /** Formatter for converting inputs in the HHmm dd/MM/yy format into LocalDateTime objects */
    private static DateTimeFormatter inputFormatter =
            DateTimeFormatter.ofPattern("[HHmm dd/MM/yy][dd MMM yyyy hh:mma]");
    /** Formatter for converting LocalDateTime objects into the dd MMM yyyy hh:mma format for outputting */
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");

    /** Start time of the event */
    private LocalDateTime from;
    /** End time of the event */
    private LocalDateTime to;

    /**
     * Constructs a new Event with the given description, start and end date/time.
     *
     * @param description Description of the event.
     * @param from Start time of the event, in the HHmm dd/MM/yy format.
     * @param to End time of the event, in the HHmm dd/MM/yy format.
     * @throws PageException If date is not in the required format.
     */
    public Event(String description, String from, String to) throws PageException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, inputFormatter);
            this.to = LocalDateTime.parse(to, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new PageException("Please format the date and time like this: 2359 31/12/99");
        }
    }

    /**
     * Returns the String representation of the event.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " from: "
                + from.format(outputFormatter) + " to: " + to.format(outputFormatter);
    }
}

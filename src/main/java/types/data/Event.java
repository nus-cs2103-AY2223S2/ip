package types.data;

import static types.data.Deadline.DATE_TIME_FORMATTER;

import java.time.LocalDateTime;

import utilities.DateTimeParser;

/**
 * Event data type.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    private Event(String n, String d1, String d2) {
        super(n, "E");
        start = DateTimeParser.parse(d1);
        end = DateTimeParser.parse(d2);
    }

    /**
     * Factory method to get a new Event object.
     * @param n Description.
     * @param d1 Starting date or time.
     * @param d2 Ending date or time.
     * @return Newly created Event object.
     */
    public static Event create(String n, String d1, String d2) {
        return new Event(n, d1, d2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s (from: %s, to: %s)",
                super.toString(),
                start.format(DATE_TIME_FORMATTER),
                end.format(DATE_TIME_FORMATTER));
    }
}

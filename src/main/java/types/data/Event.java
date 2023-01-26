package types.data;

import utilities.DateTimeParser;

import java.time.LocalDateTime;

import static types.data.Deadline.format;

public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    private Event(String n, String d1, String d2) {
        super(n, "E");
        start = DateTimeParser.parse(d1);
        end = DateTimeParser.parse(d1);
    }

    public static Event create(String n, String d1, String d2) {
        return new Event(n, d1, d2);
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s, to: %s)", super.toString(), start.format(format), end.format(format));
    }
}

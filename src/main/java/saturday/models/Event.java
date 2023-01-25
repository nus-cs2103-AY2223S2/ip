package saturday.models;

import java.time.temporal.TemporalAccessor;

import saturday.utilities.DateTimeParser;
public class Event extends Task {
    private TemporalAccessor from;
    private TemporalAccessor to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = DateTimeParser.parseDate(from);
        this.to = DateTimeParser.parseDate(to);
    }

    public TemporalAccessor getFrom() {
        return this.from;
    }

    public TemporalAccessor getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeParser.printDateTime(from) + " to: "
                + DateTimeParser.printDateTime(to) + ")";
    }
}

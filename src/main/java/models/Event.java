package models;

import utilities.DateTimeParser;

import java.time.temporal.TemporalAccessor;

public class Event extends Task {
    private TemporalAccessor from;
    private TemporalAccessor to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = DateTimeParser.parseDate(from);
        this.to = DateTimeParser.parseDate(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeParser.printDateTime(from) + " to: " + DateTimeParser.printDateTime(to) + ")";
    }
}

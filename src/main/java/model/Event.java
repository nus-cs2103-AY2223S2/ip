package model;

import utils.DateTimeParser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public static final String TAG = "[E]";

    private String start;
    private String end;

    public Event(String title, String start, String end) throws DateTimeException {
        super(title);

        try {
            LocalDateTime d1 = DateTimeParser.parse(start);
            LocalDateTime d2 = DateTimeParser.parse(end);

            if (d2.isBefore(d1)) {
                throw new DateTimeException("/to time cannot be before /from time");
            }
            this.start = DateTimeParser.format(d1);
            this.end = DateTimeParser.format(d2);
        } catch (DateTimeParseException e) {
            this.start = start;
            this.end = end;
        }
    }

    @Override
    public String toString() {
        return Event.TAG + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}

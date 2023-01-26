package task;

import helper.DateTimeHelper;

import exception.InvalidDateFormatException;

import java.time.LocalDateTime;

/**
 * Represents an event task that lasts between two time periods.
 */
public class Event extends Task{
    private LocalDateTime start;
    private LocalDateTime end;

    Event(String content, String startString, String endString) throws InvalidDateFormatException {
        super(content);
        this.start = DateTimeHelper.parse(startString);
        this.end = DateTimeHelper.parse(endString);
    }

    /**
     * Checks if the given datetime occurs within the event period.
     *
     * @param dt the given datetime to check.
     * @return Whether the given datetime occurs within the event period.
     */
    public boolean occursOn(LocalDateTime dt) {
        return dt.equals(this.start) || (dt.isAfter(this.start) && dt.isBefore(this.end)) || dt.equals(this.end);
    }

    Event(String content, boolean done, String startString, String endString) throws InvalidDateFormatException {
        super(content, done);
        this.start = DateTimeHelper.parseFormattedDateTime(startString);
        this.end = DateTimeHelper.parseFormattedDateTime(endString);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeHelper.stringify(this.start) + " to: " + DateTimeHelper.stringify(this.end) + ")";
    }
}

package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import exceptions.NoTaskDescriptionException;
import parser.ParsedDate;

/**
 * This class represents an Event Task, which will happen between a start and end date and time
 */
public class Event extends Task {

    private ParsedDate startTime;
    private ParsedDate endTime;

    /**
     * Creates an event task that start at a specific date or time and ends at a specific date or time
     *
     * @param name {@inheritDoc}
     * @param startTime date that the task starts
     * @param endTime date that the task ends
     * @throws NoTaskDescriptionException
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) throws NoTaskDescriptionException {
        super(name, "Event");
        this.startTime = new ParsedDate(startTime);
        this.endTime = new ParsedDate(endTime);
    }

    /**
     * Checks whether this event spans over the date
     *
     * @param date date to compare with this event's dates
     */
    @Override
    public boolean contains(LocalDate date) {
        return (this.startTime.isEqualDate(date) || this.startTime.isBeforeDate(date))
                && (this.endTime.isEqualDate(date) || this.endTime.isAfterDate(date));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E] " + super.toString()
                + " ( from: " + this.startTime
                + " ) ( to: " + this.endTime + " )";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String stringifyTaskToSave() {
        return "EVENT|" + super.stringifyTaskToSave() + "|" + this.startTime + "|" + this.endTime;
    }
}

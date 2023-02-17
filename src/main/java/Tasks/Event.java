package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import exceptions.NoTaskDescriptionException;
import parser.ParsedDate;

/**
 * This class represents an Event Task, which will happen between a start and end date and time
 */
public class Event extends Task{

    private ParsedDate startTime;
    private ParsedDate endTime;

    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) throws NoTaskDescriptionException {
        super(name, "Event");
        this.startTime = new ParsedDate(startTime);
        this.endTime = new ParsedDate(endTime);
    }

    /**
     * Check whether the event spans over the date
     * 
     * @param date date to compare endDate to
     */
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

    @Override 
    public String stringifyTaskToSave() {
        return "EVENT|" + super.stringifyTaskToSave() + "|" + this.startTime + "|" + this.endTime;

    }
}

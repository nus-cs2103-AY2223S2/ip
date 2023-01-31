package tasks;

import java.time.LocalDate;
>>>>>>> branch-level-8
import java.time.LocalDateTime;

import exceptions.NoTaskDescriptionException;
import parsing.ParsedDate;

/**
 * This class represents an Event Task, which will happen between a start and end date and time
 */
public class Event extends Task{

    private ParsedDate startTime;
    private ParsedDate endTime;

    protected Event(String name, LocalDateTime startTime, LocalDateTime endTime) throws NoTaskDescriptionException{
>>>>>>> branch-level-8
        super(name, "Event");
        this.startTime = new ParsedDate(startTime);
        this.endTime = new ParsedDate(endTime);
    }

    /**
     * Check whether the task is due on the date
     * 
     * @param date date to compare endDate to
     */
    public boolean isEqualDate(LocalDate date) {
        return (this.startTime.isEqualDate(date) || this.startTime.isBeforeDate(date)) 
                && (this.endTime.isEqualDate(date) || this.endTime.isAfterDate(date));
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public String toString() {
<<<<<<< HEAD
        return "[E] " + super.toString()
                + " ( from: " + this.startTime 
                + " ) ( to: " + this.endTime + " )";
    }

    @Override 
    protected String stringifyTaskToSave() {
        return "E|" + super.stringifyTaskToSave() + "|" + this.startTime + "|" + this.endTime;

    }
}

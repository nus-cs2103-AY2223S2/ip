package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import exceptions.NoTaskDescriptionException;
import parsing.ParsedDate;

public class Event extends Task{
    private ParsedDate startTime;
    private ParsedDate endTime;

    protected Event(String name, LocalDateTime startTime, LocalDateTime endTime) throws NoTaskDescriptionException{
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

    @Override
    public String toString() {
        return "[E] " + this.TasktoString()
            + " ( from: " + this.startTime 
            + " ) ( to: " + this.endTime + " )";
    }
}

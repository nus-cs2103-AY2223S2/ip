package tasks;

import java.time.LocalDateTime;

import exceptions.NoTaskDescriptionException;

/**
 * This class represents an Event Task, which will happen between a start and end date and time
 */
public class Event extends Task{
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    protected Event(String name, LocalDateTime startTime, LocalDateTime endTime) throws NoTaskDescriptionException {
        super(name, "Event");
        this.startTime = startTime;
        this.endTime = endTime;
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
    protected String stringifyTaskToSave() {
        return "E|" + super.stringifyTaskToSave() + "|" + this.startTime + "|" + this.endTime;
    }
}

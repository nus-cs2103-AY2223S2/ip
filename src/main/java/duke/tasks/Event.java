package duke.tasks;

import duke.helper.DateTimeParser;

import java.time.LocalDate;

/**
 * A class that encapsulates an event a user has to participate in.
 *
 * @author Tan Matthew Simon Castaneda
 * @version CS2103 AY22/23 Semester 2
 */
public class Event extends Task {


    protected LocalDate startTime;


    static final String type  = "E";


    protected LocalDate endTime;


    //getter for type
    public static String getType() {
        return type;
    }

    //getter for starttime
    public LocalDate getStartTime() {
        return this.startTime;
    }

    //getter for endtime
    public LocalDate getEndTime() {
        return this.endTime;
    }

    /**
     * Constructor to create event object associated with user's event.
     *
     * @param name Name of the associated event.
     * @param startTime Timing that the event starts.
     * @param endTime Timing that the event ends.
     */
    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = DateTimeParser.parse(startTime);
        this.endTime = DateTimeParser.parse(endTime);
    }

    /**
     * Converts a user's event to its string representation.
     *
     * @return A string representation of the user's event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime.toString()
                + " to: " + endTime.toString() + ")";
    }
}

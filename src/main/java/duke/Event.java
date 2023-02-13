package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representation of the Event task
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    protected DateTimeFormatter timeFormat;

    /**
     * Constructor for the Event class
     * @param description description of the task
     * @param from start date
     * @param to end date
     * @param isDone whether the task is completed
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, Boolean isDone) {
        super(description, 'E', isDone);
        this.from = from;
        this.to = to;
        this.timeFormat = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
    }

    /**
     * Returns a string representation of this event task
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from.format(timeFormat) + " to:" + to.format(timeFormat) + ")";
    }

    public static Task parseCommand(String str) throws DukeException {
        String[] detailE = str.split(" /at ", 2);
        if (detailE.length == 1) {
            throw new DukeException("When is the event?");
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime fromTime = LocalDateTime.parse(detailE[1], formatter1);
        LocalDateTime toTime = LocalDateTime.parse(detailE[2], formatter1);
        Event newE = new Event(detailE[0], fromTime, toTime, false);
        return newE;
    }

    /**
     * Returns a string representation of what is saved in the database
     * @return String
     */
    @Override
    public String savedAs() {
        return (super.savedAs() + "|" + this.from.format(timeFormat) + "|" + this.to.format(timeFormat));
    }
}

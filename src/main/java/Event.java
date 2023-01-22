/**
 * Subclass of Task class used by Duke to keep track of user's tasks inputted.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */

public class Event extends Task {

    /**
     * A string representing the start of this Event instance.
     */
    protected String from;

    /**
     * A string representation of the end of this Event instance.
     */
    protected String to;

    /**
     * Constructor for an Event instance.
     *
     * @param description String describing this Deadline.
     *
     * @param from String representing the start of this Event.
     *
     * @param to String representing the end of this Event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of an Event.
     *
     * @return The string representation of an Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from +
                " to: " + this.to + ")";
    }

    @Override
    public String getFileFormatString() {
        //to be split using "|"
        return "E" + "@" + this.isDone + "@" + this.description + "@"
                + this.from + "@" + this.to;
    }
}

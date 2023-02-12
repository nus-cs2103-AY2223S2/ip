package babe.task;

/**
 * <code>Event</code> class represents a simple <code>Event</code> with just a description, done status,
 * to and from date and time. It extends from <code>Task</code></code> as implied.
 */
public class Event extends Task {

    /**
     * The start date/time for this <code>Event</code>.
     */
    private String fromDate = "";

    /**
     * The end date/time for this <code>Event</code>.
     */
    private String toDate = "";

    /**
     * Constructor for <code>Event</code> object.
     *
     * @param content  Description of this <code>Event</code>.
     * @param fromDate The start date/time for this <code>Event</code>.
     * @param toDate   The end date/time for this <code>Event</code>.
     */
    public Event(String content, String fromDate, String toDate) {
        super(content, "E");
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    /**
     * Returns String representation of the <code>Event</code> item.
     *
     * @return A String that represents the <code>Event</code> item, including its description and done status.
     */
    public String toString() {
        return String.format("[%s] ", this.letterType)
                + (this.isDone ? this.MARKED : this.UNMARKED) + " " + this.description
                + " (from: " + this.fromDate + ", to: " + this.toDate + ")";
    }

    /**
     * Returns String representation of the <code>Event</code> object for data storage.
     * The format is as such: E|{done status}|{description}|{fromDate}|{toDate}
     *
     * @return A String representing this <code>Event</code> object.
     */
    public String toSaveFormat() {
        return "E|" + (this.isDone ? 1 : 0) + "|" + this.description + "|" + this.fromDate + "|"
                + this.toDate;

    }

    ;
}

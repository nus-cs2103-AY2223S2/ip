package babe.task;

public class Event extends Task {

    /**
     * The start date/time for this Event.
     */
    private String fromDate = "";

    /**
     * The end date/time for this Event.
     */
    private String toDate = "";

    /**
     * Constructor for Event object.
     *
     * @param content  Description of this Event.
     * @param fromDate The start date/time for this Event.
     * @param toDate   The end date/time for this Event.
     */
    public Event(String content, String fromDate, String toDate) {
        super(content);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return (this.isDone ? this.MARKED : this.UNMARKED) + " " + this.description
                + " (from: " + this.fromDate + ", to: " + this.toDate + ")";
    }

    /**
     * Returns String representation of the Event object for data storage.
     * Saves the content of the Event, including its isDone status, description,and the to/from date
     * into a String with the delimiter "|" to be written into a data file.
     *
     * @return A String representing this Event object.
     */
    public String toSaveFormat() {
        return "E|" + (this.isDone ? 1 : 0) + "|" + this.description + "|" + this.fromDate + "|"
                + this.toDate;

    }

    ;
}

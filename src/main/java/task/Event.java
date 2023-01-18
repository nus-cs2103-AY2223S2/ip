package task;

public class Event extends Task {

    /** The start date/time for this Event. */
    private String fromDate = "";

    /** The end date/time for this Event. */
    private String toDate = "";

    /**
     * Constructor for Event object.
     *
     * @param content Description of this Event.
     * @param fromDate The start date/time for this Event.
     * @param toDate The end date/time for this Event.
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
}

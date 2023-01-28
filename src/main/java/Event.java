/**
 * Class that defines the Event type of tasks
 */
public class Event extends Task {

    /** Start date for this event */
    protected String fromDate;
    /** End date for this event */
    protected String toDate;

    /**
     * Constructor for objects of type Event
     *
     * @param userInput specifies the fromDate, toDate and title of an Event object
     */
    public Event(boolean isCompleted, String userInput) {
        super(isCompleted, userInput.substring(6, userInput.indexOf("/from ") - 1), 'E');
        this.fromDate = userInput.substring(userInput.indexOf("/from ") + 6, userInput.indexOf("/to ") - 1);
        this.toDate = userInput.substring(userInput.indexOf("/to ") + 4);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + fromDate + " to: " + toDate + ")";
    }

    @Override
    public String encode() {
        return super.encode() + " | " + this.fromDate + " | " + this.toDate;
    }
}
